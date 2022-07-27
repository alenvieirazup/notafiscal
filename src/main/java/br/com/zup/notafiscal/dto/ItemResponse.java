package br.com.zup.notafiscal.dto;

import br.com.zup.notafiscal.model.Item;

import java.math.BigDecimal;

public class ItemResponse {
    private String nome;
    private Integer quantidade;
    private BigDecimal preco;

    public ItemResponse(Item item) {
        this.nome = item.getNome();
        this.quantidade = item.getQuantidade();
        this.preco = item.getPreco();
    }

    public String getNome() {
        return nome;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public BigDecimal getPreco() {
        return preco;
    }
}
