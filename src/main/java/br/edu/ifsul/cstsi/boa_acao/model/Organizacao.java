package br.edu.ifsul.cstsi.boa_acao.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "organizacoes")
public class Organizacao extends Usuario {
    private String razaoSocial;

    @Column(unique = true)
    private String cnpj;

    @OneToMany(mappedBy = "organizacao")
    private List<Doacao> doacoes = new ArrayList<>();
}
