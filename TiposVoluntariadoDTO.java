package lp.JavaFxClient.DTO;

import java.util.List;

public class TiposVoluntariadoDTO {
    private Long id;
    private String nome;
    private List<Long> programas;

    public TiposVoluntariadoDTO() {}

	public TiposVoluntariadoDTO(Long id, String nome, List<Long> programas) {
		this.id = id;
		this.nome = nome;
		this.programas = programas;
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

	public List<Long> getProgramas() {
		return programas;
	}

	public void setProgramas(List<Long> programas) {
		this.programas = programas;
	}

   
}
