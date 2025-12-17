package lp.JavaFxClient.DTO;

import java.util.List;

public class VoluntarioDTO {
	private long id;
	private String nome;
	private String email;
	private String password;
	private String morada;
	private int telemovel;
	private String tipoUtilizador;
	private List<Long> inscricoesId;

	public VoluntarioDTO() {}

	public VoluntarioDTO(long id, String nome, String email, String password, String morada, int telemovel,
			String tipoUtilizador, List<Long> inscricoesId) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.password = password;
		this.morada = morada;
		this.telemovel = telemovel;
		this.tipoUtilizador = tipoUtilizador;
		this.inscricoesId = inscricoesId;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMorada() {
		return morada;
	}

	public void setMorada(String morada) {
		this.morada = morada;
	}

	public int getTelemovel() {
		return telemovel;
	}

	public void setTelemovel(int telemovel) {
		this.telemovel = telemovel;
	}

	public String getTipoUtilizador() {
		return tipoUtilizador;
	}

	public void setTipoUtilizador(String tipoUtilizador) {
		this.tipoUtilizador = tipoUtilizador;
	}

	public List<Long> getInscricoesId() {
		return inscricoesId;
	}

	public void setInscricoesId(List<Long> inscricoesId) {
		this.inscricoesId = inscricoesId;
	}
	
	
	
}
