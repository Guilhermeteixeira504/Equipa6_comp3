package lp.Equipa6_Comp2.service;

import lp.Equipa6_Comp2.entity.Utilizador;
import lp.Equipa6_Comp2.entity.Voluntario;
import lp.Equipa6_Comp2.repository.UtilizadorRepository;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class UtilizadorService {
	
	private final UtilizadorRepository utilizadorRepository;

	
	public UtilizadorService(UtilizadorRepository utilizadorRepository) {
			this.utilizadorRepository = utilizadorRepository;
}
	
	public List<Utilizador> getAllUtilizadores(){
		return utilizadorRepository.findAll();
	}
	
	public Utilizador getUtilizador(Long id) {
		return utilizadorRepository.findById(id).orElse(null);
	}
	
	public Utilizador createUtilizador(Utilizador u) {

        // Se for voluntário → CRIA um objeto Voluntario
        if ("USER".equalsIgnoreCase(u.getTipoUtilizador())) {
            Voluntario v = new Voluntario();
            v.setNome(u.getNome());
            v.setEmail(u.getEmail());
            v.setPassword(u.getPassword());
            v.setMorada(u.getMorada());
            v.setTelemovel(u.getTelemovel());
            return utilizadorRepository.save(v);
        }

        // Caso contrário → é um utilizador normal
        return utilizadorRepository.save(u);
    }

    public Utilizador getUtilizadorById(long id) {
        return utilizadorRepository.findById(id).orElse(null);
    }

	
	public Utilizador updateUtilizador(Long id, Utilizador u) {
		Utilizador existing = utilizadorRepository.findById(id).orElseThrow(() -> new RuntimeException("Utilizador não encontrado"));
		existing.setNome(u.getNome());
		existing.setEmail(u.getEmail());
		existing.setMorada(u.getMorada());
		existing.setPassword(u.getPassword());
		existing.setTelemovel(u.getTelemovel());
		existing.setTipoUtilizador(u.getTipoUtilizador());
		
		return utilizadorRepository.save(existing);
	}
	
	public void deleteUtilizador(Long id) {
		utilizadorRepository.deleteById(id);
	}
	
	
	//foi acrescentado findByEmail no UtilizadorRepository	
	public Utilizador login(String email, String password) {
			Utilizador utilizador = utilizadorRepository.findByEmail(email);
		    if (utilizador == null || !utilizador.getPassword().equals(password)) {
		        throw new RuntimeException("Credenciais inválidas");
		    }
		    return utilizador;
		}
		
		public Utilizador alterarPassword(String email, String novaPassword) {
		    Utilizador utilizador = utilizadorRepository.findByEmail(email);

		    if (utilizador == null) {
		        throw new RuntimeException("Email não encontrado");
		    }

		    utilizador.setPassword(novaPassword);
		    return utilizadorRepository.save(utilizador);
		}}


