package br.edu.ifsul.cstsi.boa_acao.model;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "doacoes")
public class Doacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_doacao")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_doador", nullable = false)
    private Doador doador;

    @ManyToOne
    @JoinColumn(name = "id_organizacao", nullable = false)
    private Organizacao organizacao;
    private String status;
    private LocalDateTime data_hora;

    @OneToMany(mappedBy = "doacao", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemDoacao> itens = new ArrayList<>();
}
