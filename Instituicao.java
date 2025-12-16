package lp.Equipa6_Comp2.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "instituicao")
public class Instituicao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    // uma instituição pode ter vários programas de voluntariado
    @OneToMany(mappedBy = "instituicao")
    private List<ProgramaVoluntariado> programas = new ArrayList<>();

    public Instituicao() {
    }

    // getters e setters
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

    public List<ProgramaVoluntariado> getProgramas() {
        return programas;
    }

    public void setProgramas(List<ProgramaVoluntariado> programas) {
        this.programas = programas;
    }

    public void addPrograma(ProgramaVoluntariado programa) {
        this.programas.add(programa);
        programa.setInstituicao(this);
    }

    @Override
    public String toString() {
        return "Instituicao{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", numeroProgramas=" + programas.size() +
                '}';
    }
}