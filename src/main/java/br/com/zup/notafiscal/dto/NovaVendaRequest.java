package br.com.zup.notafiscal.dto;


import br.com.zup.notafiscal.model.Item;
import br.com.zup.notafiscal.model.NotaFiscal;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class NovaVendaRequest {
    private String codigoPedido;
    private CompradorRequest comprador;
    private List<ItemRequest> itens;
    private PagamentoRequest pagamento;

    public NovaVendaRequest() {
    }

    public NovaVendaRequest(String codigoPedido, CompradorRequest comprador, List<ItemRequest> itens, PagamentoRequest pagamento) {
        this.codigoPedido = codigoPedido;
        this.comprador = comprador;
        this.itens = itens;
        this.pagamento = pagamento;
    }

    public String getCodigoPedido() {
        return codigoPedido;
    }

    public CompradorRequest getComprador() {
        return comprador;
    }

    public List<ItemRequest> getItens() {
        return itens;
    }

    public PagamentoRequest getPagamento() {
        return pagamento;
    }

    public NotaFiscal toModel() {
        List<Item> novosItens = itens.stream()
                .map(i -> i.toModel())
                .collect(Collectors.toList());
        BigDecimal valorTotal = itens.stream()
                .map(v -> v.getPreco().multiply(new BigDecimal(v.getQuantidade())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return new NotaFiscal(novosItens, comprador.getNome(), comprador.getCpf(),
                comprador.getEndereco(), comprador.getEmail(), valorTotal);
    }

}
