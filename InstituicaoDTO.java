package DTO;

import java.util.List;

public class InstituicaoDTO {

    private Long id;
    private String nome;

    // ids dos programas associados
    private List<Long> programaIds;

    public InstituicaoDTO() {}

    public InstituicaoDTO(Long id, String nome, List<Long> programaIds) {
        this.id = id;
        this.nome = nome;
        this.programaIds = programaIds;
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

    public List<Long> getProgramaIds() {
        return programaIds;
    }

    public void setProgramaIds(List<Long> programaIds) {
        this.programaIds = programaIds;
    }
}