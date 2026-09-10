package br.edu.ifsul.cstsi.boa_acao.model;

import jakarta.persistence.*;


@Entity
@Table(name = "usuarios")
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long id;
    private String nome;
    private String sobrenome;
    @Column(unique = true)
    private String email;
    private String tipo;
    private String senha;
    private boolean isConfirmado = false;
    private Byte situacao;
}
