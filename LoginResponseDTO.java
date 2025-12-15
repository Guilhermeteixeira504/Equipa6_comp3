package DTO;

public class LoginResponseDTO {
	private long id;
    private String nome;
    private String email;
    private String tipoUtilizador;
	
    public LoginResponseDTO() {}
    
    public LoginResponseDTO(long id, String nome, String email, String tipoUtilizador) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.tipoUtilizador = tipoUtilizador;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTipoUtilizador() {
		return tipoUtilizador;
	}

	public void setTipoUtilizador(String tipoUtilizador) {
		this.tipoUtilizador = tipoUtilizador;
	}
    
    

}
