package br.com.zup.notafiscal.dto;

import br.com.zup.notafiscal.model.Item;

import java.math.BigDecimal;

public class ItemRequest {
    private Long id;
    private String nome;
    private Integer quantidade;
    private BigDecimal preco;

    public ItemRequest() {
    }

    public ItemRequest(Long id, String nome, Integer quantidade, BigDecimal preco) {
        this.id = id;
        this.nome = nome;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    public Long getId() {
        return id;
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

    public Item toModel() {
        return new Item(nome, quantidade, preco);
    }

}
