package br.edu.ifsul.cstsi.boa_acao.model;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "doadores")
public class Doador extends Usuario {
    @Column(unique = true)
    private String cpf;
    private Boolean anonimizar;
    @OneToMany(mappedBy = "doador")
    private List<Doacao> doacoes = new ArrayList<>();
}
