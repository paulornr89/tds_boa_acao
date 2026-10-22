package br.edu.ifsul.cstsi.boa_acao.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ItemDtoPost(
        @NotBlank(message = "A descrição não pode ser nula ou vazia")
        @Size(min = 5, max = 50, message = "Tamanho mínimo de 5 e máximo de 50")
        String descricao,
        @NotBlank(message = "A unidade não pode ser nula ou vazia")
        @Size(min = 1, max = 3, message = "Tamanho mínimo de 1 e máximo de 3")
        String unidade,
        @NotBlank(message = "O tipo não pode ser nulo ou vazio")
        @Size(min = 5, max = 20, message = "Tamanho mínimo de 5 e máximo de 20")
        String tipo
) {
    public ItemDtoPost(Item item){
        this(item.getDescricao(), item.getUnidade(), item.getTipo());
    }
}