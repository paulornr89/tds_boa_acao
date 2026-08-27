package br.edu.ifsul.cstsi.boa_acao.model;
import jakarta.persistence.*;
@Entity
@Table(name = "item_doacao")
public class ItemDoacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_doacao")
    private Doacao doacao;

    @ManyToOne
    @JoinColumn(name = "id_item")
    private Item item;

    @Column(nullable = false)
    private Integer quantidade;
}
