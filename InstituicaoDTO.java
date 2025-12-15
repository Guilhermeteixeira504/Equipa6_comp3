package lp.JavaFxClient.DTO;

import java.time.LocalDate;
import java.util.List;

public class InstituicaoDTO {

    private Long id;
    private String nome;
    private LocalDate dataFundacao;

    // EM VEZ DE ENTIDADES → IDs
    private List<Long> programaIds;
    private Long programaId;

    public InstituicaoDTO() {}

    public InstituicaoDTO(Long id, String nome, LocalDate dataFundacao,
                          List<Long> programaIds, Long programaId) {

        this.id = id;
        this.nome = nome;
        this.dataFundacao = dataFundacao;
        this.programaIds = programaIds;
        this.programaId = programaId;
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

    public LocalDate getDataFundacao() { 
    	return dataFundacao; 
    	}
    public void setDataFundacao(LocalDate dataFundacao) { 
    	this.dataFundacao = dataFundacao;
    	}

    public List<Long> getProgramaIds() { 
    	return programaIds;
    	}
    public void setProgramaIds(List<Long> programaIds) { 
    	this.programaIds = programaIds;
    	}

    public Long getProgramaId() { 
    	return programaId; 
    	}
    public void setProgramaId(Long programaId) { 
    	this.programaId = programaId; 
    	}
}
