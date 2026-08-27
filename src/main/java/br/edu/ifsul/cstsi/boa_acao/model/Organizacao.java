package br.edu.ifsul.cstsi.boa_acao.model;

import jakarta.persistence.*;

@Entity
@Table(name = "organizacoes")
public class Organizacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_organizacao")
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_usuario", nullable = false, unique = true)
    private Usuario usuario;
    private String razaoSocial;

    @Column(unique = true)
    private String cnpj;
}
