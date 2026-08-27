package br.edu.ifsul.cstsi.boa_acao.model;


import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "itens")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    private String unidade;
    private String tipo;
}
