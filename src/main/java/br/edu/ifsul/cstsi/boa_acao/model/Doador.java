package br.edu.ifsul.cstsi.boa_acao.model;
import jakarta.persistence.*;

@Entity
@Table(name = "doadores")
public class Doador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_doador")
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_usuario", nullable = false, unique = true)
    private Usuario usuario;
    @Column(unique = true)
    private String cpf;
}
