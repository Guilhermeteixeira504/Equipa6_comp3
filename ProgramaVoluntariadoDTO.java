package lp.JavaFxClient.DTO;

import java.time.LocalDate;
import java.util.List;

public class ProgramaVoluntariadoDTO {

    private Long id;
    private String titulo;
    private String descricao;
    private String localizacao;
    private int numeroVagas;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private int totalHoras;
    private List<Long> inscricoesId;
	private List<Long> edicoesId;
	private Long tiposVoluntariadoId;
	private Long instituicaoId;

    public ProgramaVoluntariadoDTO() {}

    
    


	public ProgramaVoluntariadoDTO(Long id, String titulo, String descricao, String localizacao, int numeroVagas,
			LocalDate dataInicio, LocalDate dataFim, int totalHoras, List<Long> inscricoesId, List<Long> edicoesId,
			Long tiposVoluntariadoId, Long instituicaoId) {
		this.id = id;
		this.titulo = titulo;
		this.descricao = descricao;
		this.localizacao = localizacao;
		this.numeroVagas = numeroVagas;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.totalHoras = totalHoras;
		this.inscricoesId = inscricoesId;
		this.edicoesId = edicoesId;
		this.tiposVoluntariadoId = tiposVoluntariadoId;
		this.instituicaoId = instituicaoId;
	}







    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getLocalizacao() {
        return localizacao;
    }
    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public int getNumeroVagas() {
        return numeroVagas;
    }
    public void setNumeroVagas(int numeroVagas) {
        this.numeroVagas = numeroVagas;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }
    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }
    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getTotalHoras() {
		return totalHoras;
	}

	public void setTotalHoras(int totalHoras) {
		this.totalHoras = totalHoras;
	}
	

	public List<Long> getInscricoesId() {
		return inscricoesId;
	}


	public void setInscricoesId(List<Long> inscricoesId) {
		this.inscricoesId = inscricoesId;
	}
	
	public void addInscricao(Long id) {
		inscricoesId.add(id);
	}


	public List<Long> getEdicoesId() {
		return edicoesId;
	}


	public void setEdicoesId(List<Long> edicoesId) {
		this.edicoesId = edicoesId;
	}

	public void addEdicao(Long id) {
        edicoesId.add(id);
    }

	public Long getTiposVoluntariadoId() {
		return tiposVoluntariadoId;
	}


	public void setTiposVoluntariadoId(Long tiposVoluntariadoId) {
		this.tiposVoluntariadoId = tiposVoluntariadoId;
	}


	public Long getInstituicaoId() {
		return instituicaoId;
	}


	public void setInstituicaoId(Long instituicaoId) {
		this.instituicaoId = instituicaoId;
	}
    
}