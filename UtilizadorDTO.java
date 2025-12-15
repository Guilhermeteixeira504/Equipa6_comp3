package lp.JavaFxClient.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)//ignora campos não preenchidos
public class UtilizadorDTO {
	
	private Long id;
	private String nome;
	private String email;
	private String password;
	private String morada;
	private int telemovel;
	private String tipoUtilizador;
	
	public UtilizadorDTO() {}

	public UtilizadorDTO(Long id, String nome, String email, String password, String morada, int telemovel,
			String tipoUtilizador) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.password = password;
		this.morada = morada;
		this.telemovel = telemovel;
		this.tipoUtilizador = tipoUtilizador;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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
}