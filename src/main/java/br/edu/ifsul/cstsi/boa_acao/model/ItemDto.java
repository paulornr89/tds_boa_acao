package br.edu.ifsul.cstsi.boa_acao.model;

import java.io.Serializable;

/**
 * DTO for {@link Item}
 */
public record ItemDto(Long id, String descricao, String unidade, String tipo) implements Serializable {
    public ItemDto (Item item) {
      this(item.getId(), item.getDescricao(), item.getUnidade(), item.getTipo());
    }
}