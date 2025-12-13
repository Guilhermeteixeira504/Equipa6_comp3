package lp.JavaFxClient.DTO;

public class InscricaoRequestDTO {

    private Long programaId;
    private Long voluntarioId;

    public InscricaoRequestDTO() {}

    public InscricaoRequestDTO(Long programaId, Long voluntarioId) {
        this.programaId = programaId;
        this.voluntarioId = voluntarioId;
    }

    public Long getProgramaId() {
        return programaId;
    }

    public void setProgramaId(Long programaId) {
        this.programaId = programaId;
    }

    public Long getVoluntarioId() {
        return voluntarioId;
    }

    public void setVoluntarioId(Long voluntarioId) {
        this.voluntarioId = voluntarioId;
    }
}