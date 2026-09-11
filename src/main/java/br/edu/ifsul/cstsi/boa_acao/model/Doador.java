package br.edu.ifsul.cstsi.boa_acao.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "doadores")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Doador extends Usuario {
    @Column(unique = true)
    private String cpf;
    private Boolean anonimizar;
    @OneToMany(mappedBy = "doador")
    private List<Doacao> doacoes = new ArrayList<>();
}
