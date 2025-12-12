package lp.JavaFxClient.model;

import java.time.LocalDate;

public class ProgramaVoluntariadoDTO {

    private long id;
    private String titulo;
    private String localizacao;
    private int numeroVagas;
    private LocalDate dataInicio;
    private LocalDate dataFim;

    public ProgramaVoluntariadoDTO() {}

    public long getId() {
        return id;
    }
    public void setId(long id) {
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
}