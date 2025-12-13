package cliente_Consola;

import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import lp.Equipa6_Comp2.entity.Utilizador;
import lp.Equipa6_Comp2.entity.Voluntario;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {
	
	private static final String BASE_URL = "http://localhost:8080";
	private static final RestTemplate rest = new RestTemplate();
	private static final Scanner ler = new Scanner(System.in);

	public static void main(String[] args) {
		while (true) {
            System.out.println("\n===== SISTEMA DE VOLUNTARIADO =====");
            System.out.println("1) Login");
            System.out.println("2) Registar novo Utilizador");
            System.out.println("0) Sair");
            System.out.print("Escolha: ");
            int opcao = ler.nextInt();

            switch (opcao) {
                case 1 -> login();
                case 2 -> registarUtilizador();
                case 0 -> {
                	System.out.println("A sair ...");
                	return;
                }
                default -> System.out.println("Opção inválida!!!");
            }
        }
	}
	
	//registo
	private static void registarUtilizador() {
		System.out.println("\n Registar novo utilizador");
        System.out.print("Nome: ");
        String nome = ler.next();
        System.out.print("Email: ");
        String email = ler.next();
        System.out.print("Password: ");
        String pass = ler.next();
        System.out.print("Morada: ");
        String morada = ler.next();
        System.out.print("Telemóvel: ");
        int tel = ler.nextInt();
        
        String tipoUtilizador;
        while (true) {
            System.out.print("Tipo de utilizador (1-Admin | 2-Utilizador ): ");
            int escolha = ler.nextInt();
            if (escolha==1){
            	tipoUtilizador  = "ADMIN";
            	break; 
            	}
            if (escolha==2) { 
            	tipoUtilizador = "USER";
            	break; 
            	}
            System.out.println("Escolha inválida. Introduza 1 para Admin ou 2 para Utilizador.");
        }
        
        String json = """
        		{
        			"nome" : "%s",
        			"email" : "%s",
        			"password" : "%s",
        			"morada" : "%s",
        			"telemovel" : %d,
        			"tipoUtilizador" : "%s"
        		}
        		""".formatted(nome,email,pass,morada,tel,tipoUtilizador);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        HttpEntity<String> request = new HttpEntity<>(json, headers);
        
        if(tipoUtilizador.equalsIgnoreCase("USER")) {
        	String response = rest.postForObject(BASE_URL + "/voluntariado/voluntarios", request, String.class);
        	
        	System.out.println("\nRegisto efetuado com sucesso como " + tipoUtilizador + ":");
            System.out.println(response);
        }
        
        else {
        	String response = rest.postForObject(BASE_URL + "/voluntariado/utilizadores", request, String.class);
        	
        	System.out.println("\nRegisto efetuado com sucesso como " + tipoUtilizador + ":");
            System.out.println(response);
        }
        
	}
		
	//login
	private static void login() {
		System.out.println("\n--- Login ---");
		String tipoUtilizador;
		while (true) {
            System.out.print("Tipo de utilizador (1-Admin | 2-Utilizador ): ");
            int escolha = ler.nextInt();
            if (escolha==1){
            	tipoUtilizador  = "ADMIN";
            	break; 
            	}
            if (escolha==2) { 
            	tipoUtilizador = "USER";
            	break; 
            	}
		}
		
        System.out.print("Email: ");
        String email = ler.next();
        System.out.print("Password: ");
        String password = ler.next();

       
        
        try {
        	
        	String json;

        	if("ADMIN".equalsIgnoreCase(tipoUtilizador)) {
        	    json = """
        	    {
        	        "email" : "%s",
        	        "password" : "%s"
        	    }
        	    """.formatted(email, password);
        	    
        	    HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                
                HttpEntity<String> request = new HttpEntity<>(json, headers);
        	    
        	    ResponseEntity<String> response = rest.exchange(
        	        BASE_URL + "/voluntariado/utilizadores/login",
        	        HttpMethod.POST, request, String.class
        	    );
        	    
        	    String responseBody = response.getBody();
           	 // converter JSON → objeto 
           	    ObjectMapper mapper = new ObjectMapper(); 
           	    Utilizador utilizador = mapper.readValue(responseBody, Utilizador.class); 
           	    System.out.println("\nLogin efetuado com sucesso! Bem-vindo, " + utilizador.getNome() + "."); 
           	    menuAdmin(utilizador);
        	    
        	    
        	} else {
        	    json = """
        	    {
        	        "email" : "%s",
        	        "password" : "%s"
        	    }
        	    """.formatted(email, password);
        	    
        	    HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                
                HttpEntity<String> request = new HttpEntity<>(json, headers);
        	    
        	    ResponseEntity<String> response = rest.exchange(
        	        BASE_URL + "/voluntariado/voluntarios/login",
        	        HttpMethod.POST, request, String.class
        	    );
        	    
        	    String responseBody = response.getBody();
        	 // converter JSON → objeto 
        	    ObjectMapper mapper = new ObjectMapper(); 
        	    Voluntario voluntario = mapper.readValue(responseBody, Voluntario.class); 
        	    System.out.println("\nLogin efetuado com sucesso! Bem-vindo, " + voluntario.getNome() + "."); 
        	    menuUtilizador(voluntario);
        	}
        	
        	
        
        } catch (Exception e) {
        	System.out.println("Credenciais inválidas!");
        	
        	System.out.print("Esqueceu a password? (s/n): ");
            String opcao = ler.next();

            if (opcao.equalsIgnoreCase("s")) {
            	alterarPasswordLogin(email);
            }
        }
        
	}
	
	// no login se o utilizador introduzir uma password inválida pode alterá-la
	private static void alterarPasswordLogin(String email) {
        System.out.print("Introduz a nova password: ");
        String novaPass = ler.next();

        String json = """
                {
                   "email": "%s",
                   "novaPassword": "%s"
                }
                """.formatted(email, novaPass);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<>(json, headers);

        try {
            String response = rest.exchange(BASE_URL + "/voluntariado/utilizadores/alterarpassword",HttpMethod.PUT,request,String.class).getBody();

            System.out.println("Password alterada com sucesso! Tente fazer login novamente.");

        } catch (Exception e) {
            System.out.println("Erro ao alterar password: " + e.getMessage());
        }
    }		
			
			
	//menu admin
	private static void menuAdmin(Utilizador u) {
		while (true) {
			System.out.println("\n===== MENU ADMIN =====");
			System.out.println("1) Criar tipos de voluntariado");
			System.out.println("2) Registar instituição");
            System.out.println("3) Criar programa de voluntariado");
            System.out.println("4) Criar edição");
            System.out.println("5) Listar programas");
            System.out.println("6) Procurar programa por ID");
            System.out.println("7) Listar utilizadores");
            System.out.println("8) Listar voluntários");
            System.out.println("9) Listar inscrições");
            System.out.println("10) Definir horas trabalhadas por voluntário");
            System.out.println("11) Listar instituições");
            System.out.println("0) Terminar sessão");
            System.out.print("Escolha: ");
            int opcao = ler.nextInt();

            switch (opcao) {
                case 1 -> criarTiposVoluntariado();
                case 2 -> registarInstituicao();
                case 3 -> criarPrograma();
                case 4 -> criarEdicao();
                case 5 -> listarProgramas();
                case 6 -> procurarProgramaPorId();
                case 7 -> listarUtilizadores();
                case 8 -> listarVoluntarios();
                case 9 -> listarInscricoes();
                case 10 -> definirHorasTrabalhadas();
                case 11 -> listarInstituicoes();
                case 0 -> {
                    System.out.println(" Sessão terminada. A voltar ao menu principal...");
                    return;
                }
                default -> System.out.println(" Opção inválida!");
            }
        }
	}
	
	private static void criarTiposVoluntariado() {
		System.out.println("\n--- Novo Tipo de Voluntariado ---");
		
		ler.nextLine();
        System.out.print("Nome: ");    
        String nome = ler.nextLine().trim();
        
        String json = """
        		{
        			"nome" : "%s"
        		}
        		""".formatted(nome);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        HttpEntity<String> request = new HttpEntity<>(json, headers);
        
        String response = rest.postForObject(BASE_URL + "/voluntariado/tiposvoluntariados", request, String.class);
        
        System.out.println("\nTipo de voluntariado criado:");
        System.out.println(response);
	}
	
	private static void registarInstituicao() {
		ler.nextLine();
		System.out.println("\n---Menu Instituição---");
        System.out.print("Nome da instituição: ");       
        String nome = ler.nextLine().trim();

        System.out.print("Contacto: ");    
        long df = ler.nextLong();
        
        System.out.println("Id do programa: ");
        long programaId = ler.nextLong();
        
        String json = """
        		{
        			"nome" : "%s",
        			"contacto" : "%d",
        			"programaId" : %d
        		}
        		""".formatted(nome,df,programaId);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        HttpEntity<String> request = new HttpEntity<>(json, headers);
        
        String response = rest.postForObject(BASE_URL + "/voluntariado/instituicoes", request, String.class);
        
        System.out.println("\nInstituição registada:");
        System.out.println(response);
	}
	
	private static void criarPrograma() {
        System.out.println("\n--- Novo Programa de Voluntariado ---"); 
        
        ler.nextLine();
        System.out.print("Título: ");       
        String titulo = ler.nextLine().trim();

        System.out.print("Descrição: ");    
        String descricao = ler.nextLine().trim();

        System.out.print("Localização: ");  
        String localizacao = ler.nextLine().trim();

        System.out.print("Nº de vagas: ");  
        int vagas = Integer.parseInt(ler.nextLine().trim());  // lê linha e converte

        System.out.print("Data fim (AAAA-MM-DD): "); 
        LocalDate df = lerData();

        System.out.println("Data inicio (AAAA-MM-DD): ");    
        LocalDate di = lerData();
        
        if (df.isBefore(di)) {
            System.out.println("Erro: Data de fim não pode ser anterior à data de início!");
            return;}

        System.out.print("Total de horas: ");           
        int total = Integer.parseInt(ler.nextLine().trim());
        
        System.out.println("ID do tipo de voluntariado: ");
        long tiposVoluntariadoId = ler.nextLong();

        String json = """
        		{
        			"titulo" : "%s",
        			"descricao" : "%s",
        			"localizacao" : "%s",
        			"numeroVagas" : %d,
        			"dataFim" : "%s",
        			"dataInicio" : "%s",
        			"totalHoras" : %d,
        			"tiposVoluntariadoId" : %d
        		}
        		""".formatted(titulo,descricao,localizacao,vagas,di,df,total,tiposVoluntariadoId);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        HttpEntity<String> request = new HttpEntity<>(json, headers);
        
        String response = rest.postForObject(BASE_URL + "/voluntariado/programasvoluntariado", request, String.class);
        
        System.out.println("\nPrograma de voluntariado criado:");
        System.out.println(response);
    }
	
	private static void criarEdicao() {
		ler.nextLine();
		System.out.println("\n---Menu Edição---");
		System.out.print("Ano: ");  
        int ano = Integer.parseInt(ler.nextLine().trim());

        System.out.println("data de inicio: ");
        LocalDate di=lerData();
        
        if (di.getYear() != ano) {
            System.out.println("Erro: A data de início deve ser do ano " + ano + "!");
            return;}
        
        System.out.print("ID do programa de voluntariado: ");
        long programaId = ler.nextLong();
        
        String json = """
        		{
        			"ano" : "%s",
        			"dataInicio" : "%s",
        			"programaId" : %d,
        			"voluntariosInscritos": []
        			
        		}
        		""".formatted(ano,di,programaId);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        HttpEntity<String> request = new HttpEntity<>(json, headers);
        
        String response = rest.postForObject(BASE_URL + "/voluntariado/edicoes", request, String.class);
        
        System.out.println("\nEdição criada:");
        System.out.println(response);
	}
	
	private static void listarProgramas() {
		String url = BASE_URL + "/voluntariado/programasvoluntariado";
		
		try {
			String response = rest.getForObject(url, String.class);
			
			System.out.println("\n--- Lista de Programas de Voluntariado ---");
			
			response = response.replace("},{", "},\n{");
			
			System.out.println(response);
		} catch (Exception e) {
			System.out.println("Erro ao listar programas: " + e.getMessage());
		}
	}
	
	private static void procurarProgramaPorId() {
		System.out.print("ID do programa: ");
        long programaId = ler.nextLong();
        
        String url = BASE_URL + "/voluntariado/programasvoluntariado/" + programaId;
        
        try {
        	String response = rest.getForObject(url, String.class);
            System.out.println("\n--- Programa encontrado ---");
            System.out.println(response);
        } catch (Exception e) {
            System.out.println("Erro ao procurar programa: " + e.getMessage());
        }
	}
	
	private static void listarUtilizadores() {
		String url = BASE_URL + "/voluntariado/utilizadores";
		
		try {
			String response = rest.getForObject(url, String.class);
			
			System.out.println("\n--- Lista de Utilizadores ---");
			
			response = response.replace("},{", "},\n{");
			
			System.out.println(response);
		} catch (Exception e) {
			System.out.println("Erro ao listar utilizadores: " + e.getMessage());
		}
	}
	
	private static void listarVoluntarios() {
		String url = BASE_URL + "/voluntariado/voluntarios";
		
		try {
			String response = rest.getForObject(url, String.class);
			
			System.out.println("\n--- Lista de Voluntários ---");
			
			response = response.replace("},{", "},\n{");
			
			System.out.println(response);
		} catch (Exception e) {
			System.out.println("Erro ao listar voluntários: " + e.getMessage());
		}
	}
	
	private static void listarInscricoes() {
		String url = BASE_URL + "/voluntariado/inscricoes";
		
		try {
			String response = rest.getForObject(url, String.class);
			
			System.out.println("\n--- Lista de Inscrições ---");
			
			response = response.replace("},{", "},\n{");
			
			System.out.println(response);
		} catch (Exception e) {
			System.out.println("Erro ao listar inscrições: " + e.getMessage());
		}
	}
	
	private static void listarInstituicoes(){
		String url = BASE_URL + "/voluntariado/instituicoes";
		
		try {
			String response = rest.getForObject(url, String.class);
			
			System.out.println("\n--- Lista de Instituições ---");
			
			response = response.replace("},{", "},\n{");
			
			System.out.println(response);
		} catch (Exception e) {
			System.out.println("Erro ao listar instituições: " + e.getMessage());
		}
	}
	
	
	//menu Utilizador
    private static void menuUtilizador(Voluntario v) {
        while (true) {
            System.out.println("\n===== MENU UTILIZADOR =====");
            System.out.println("1) Consultar Programas de Voluntariado disponíveis");
            System.out.println("2) Inscrever-se num Programa de Voluntariado");
            System.out.println("3) Mudar Password");
            System.out.println("0) Terminar sessão");
            System.out.print("Escolha: ");
            int opcao = ler.nextInt();

            switch (opcao) {
                case 1 -> listarProgramasDisponiveis();
                case 2 -> inscreverPrograma(v);
                case 3 -> alterarPassword(v);
                case 0 -> {
                    System.out.println(" Sessão terminada. A voltar ao menu principal...");
                    return;
                }
                default -> System.out.println("Opção inválida!");
            }
        }
    }
	
    private static void listarProgramasDisponiveis() {
        String url = BASE_URL + "/voluntariado/programasvoluntariado";
        
        try {
            String response = rest.getForObject(url, String.class);
            
            System.out.println("\n--- Lista de Programas de Voluntariado ---");
            
            // Coloca cada programa em linha separada
            response = response.replace("},{", "},\n{");
            
            System.out.println(response);
            
        } catch (Exception e) {
            System.out.println("Erro ao listar programas: " + e.getMessage());
        }
    }
      
    private static void inscreverPrograma(Voluntario v) {
        listarProgramasDisponiveis();

        System.out.print("\nEscolha o ID do programa para inscrever-se: ");
        long pid = ler.nextLong();
        
        String json = """
                {
                   "programaId": %d,
                   "voluntarioId": %d,
                   "nHorasRealizadas": %d
                }
                """.formatted(pid,v.getId(),0);

        String url = BASE_URL + "/voluntariado/inscricoes/inscrever";

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> request = new HttpEntity<>(json, headers);

            String response = rest.postForObject(url, request, String.class);

            System.out.println("\nInscrição realizada com sucesso:");
            System.out.println(response);

        } catch (Exception e) {
            System.out.println("Erro ao inscrever: " + e.getMessage());
        }
    }
	
	
    public static void alterarPassword(Voluntario v) {
    	System.out.print("Insira a nova password: ");
        String novaPass = ler.next();

        String json = """
                {
                   "email": "%s",
                   "novaPassword": "%s"
                }
                """.formatted(v.getEmail(),novaPass);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<>(json, headers);

        try {
            String response = rest.exchange(BASE_URL + "/voluntariado/utilizadores/alterarpassword",HttpMethod.PUT,request,String.class).getBody();

            System.out.println("Password alterada com sucesso!");

        } catch (Exception e) {
            System.out.println("Erro ao alterar password: " + e.getMessage());
        }
    }
    
    
    private static void definirHorasTrabalhadas() {
        System.out.println("\nRegistar Horas Trabalhadas");
        
        System.out.print("ID da inscricao: ");
        long inscricaoId = ler.nextLong();
        
        try {
            String inscricaoUrl = BASE_URL + "/voluntariado/inscricoes/" + inscricaoId;
            ResponseEntity<String> inscricaoResponse = rest.getForEntity(inscricaoUrl, String.class);
            
            if (inscricaoResponse.getStatusCode() != HttpStatus.OK) {
                System.out.println("Erro: Inscrição não encontrada.");
                return;
            }
            System.out.print("Horas trabalhadas: ");
            int horas = ler.nextInt();
            
            String json = """
                    {
                       "nHorasRealizdas": %d
                    }
                    """.formatted(horas);
            
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> request = new HttpEntity<>(json, headers);

            String response = rest.exchange(
                BASE_URL + "/voluntariado/inscricoes/" + inscricaoId + "/horas",
                HttpMethod.PUT,
                request,
                String.class
            ).getBody();

            System.out.println("\nNumero de horas trabalhadas registadas com sucesso!");

        } catch (HttpClientErrorException.NotFound e) {
            System.out.println("\nErro: Inscrição não encontrada.");
        } catch (Exception e) {
            System.out.println("Erro: Operação falhou. Horas não registadas.");
            e.printStackTrace();
        }
    }

	private static LocalDate lerData() {
        while (true) {
            try { return LocalDate.parse(ler.nextLine()); }
            catch (DateTimeParseException e) { System.out.print("Data inválida (formato AAAA-MM-DD): "); }
        }
    }

}
