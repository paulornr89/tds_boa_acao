package br.edu.ifsul.cstsi.boa_acao.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "organizacoes")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Organizacao extends Usuario {
    private String razaoSocial;

    @Column(unique = true)
    private String cnpj;

    @OneToMany(mappedBy = "organizacao")
    private List<Doacao> doacoes = new ArrayList<>();
}
