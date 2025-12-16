package lp.JavaFxClient.DTO;

import java.util.List;

public class InstituicaoDTO {

    private Long id;
    private String nome;
    private List<Long> programaIds;

    public InstituicaoDTO() {}

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

    public List<Long> getProgramaIds() {
        return programaIds;
    }

    public void setProgramaIds(List<Long> programaIds) {
        this.programaIds = programaIds;
    }

    // usado pela TableView
    public String getProgramasAsString() {
        if (programaIds == null || programaIds.isEmpty()) {
            return "—";
        }
        return programaIds.toString();
    }
}