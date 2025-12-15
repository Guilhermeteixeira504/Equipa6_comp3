package lp.JavaFxClient.DTO;

public class InscricaoDTO {

    private Long id;
    private Long programaId;
    private Long voluntarioId;
    private int nHorasRealizadas;

    public InscricaoDTO() {}

    public InscricaoDTO(Long id, Long programaId, Long voluntarioId, int nHorasRealizadas) {
        this.id = id;
        this.programaId = programaId;
        this.voluntarioId = voluntarioId;
        this.nHorasRealizadas = nHorasRealizadas;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getProgramaId() { return programaId; }
    public void setProgramaId(Long programaId) { this.programaId = programaId; }

    public Long getVoluntarioId() { return voluntarioId; }
    public void setVoluntarioId(Long voluntarioId) { this.voluntarioId = voluntarioId; }

    public int getnHorasRealizadas() { return nHorasRealizadas; }
    public void setnHorasRealizadas(int nHorasRealizadas) { this.nHorasRealizadas = nHorasRealizadas; }
}