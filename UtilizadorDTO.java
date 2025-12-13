package lp.JavaFxClient.DTO;

public class UtilizadorDTO {

    private Long id;
    private String nome;
    private String email;
    private String tipoUtilizador;

    public UtilizadorDTO() {
    }

    public UtilizadorDTO(Long id, String nome, String email, String tipoUtilizador) {
        this.id = id;
        this.nome = nome;
        this.email = email;
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

    public String getTipoUtilizador() {
        return tipoUtilizador;
    }

    public void setTipoUtilizador(String tipoUtilizador) {
        this.tipoUtilizador = tipoUtilizador;
    }

    @Override
    public String toString() {
        return nome + " (" + tipoUtilizador + ")";
    }
}