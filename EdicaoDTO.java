package lp.JavaFxClient.DTO;

import java.time.LocalDate;
import java.util.List;

public class EdicaoDTO {
	private Long id;
	private int ano;
	private int numeroVagas;
	private LocalDate dataInicio;
	private LocalDate dataFim;
	private List<Long> inscricoesId;
	private Long programaId;
	public EdicaoDTO() {
		
	}
	public EdicaoDTO(Long id, int ano, int numeroVagas, LocalDate dataInicio, LocalDate dataFim,
			List<Long> inscricoesId, Long programaId) {
		this.id = id;
		this.ano = ano;
		this.numeroVagas = numeroVagas;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.inscricoesId = inscricoesId;
		this.programaId = programaId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
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
	public List<Long> getInscricoesId() {
		return inscricoesId;
	}
	public void setInscricoesId(List<Long> inscricoesId) {
		this.inscricoesId = inscricoesId;
	}
	public Long getProgramaId() {
		return programaId;
	}
	public void setProgramaId(Long programaId) {
		this.programaId = programaId;
	}
	
	
	
	
	
	

}
