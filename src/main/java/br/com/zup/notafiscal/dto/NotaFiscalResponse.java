package br.com.zup.notafiscal.dto;

import br.com.zup.notafiscal.model.NotaFiscal;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class NotaFiscalResponse {
    private String numeroDaNota;
    private LocalDateTime criadoEm;
    private String nomeComprador;
    private String cpf;
    private String endereco;
    private List<ItemResponse> itens;
    private BigDecimal valorTotal;


    public NotaFiscalResponse(NotaFiscal notaFiscal) {
        this.numeroDaNota = notaFiscal.getNumeroNota();
        this.criadoEm = notaFiscal.getCriadoEm();
        this.endereco = notaFiscal.getEndereco();
        this.nomeComprador = notaFiscal.getNomeComprador();
        this.cpf = notaFiscal.getCpf();
        this.valorTotal = notaFiscal.getValorTotal();
        this.itens = notaFiscal.getItens().stream()
                .map(ItemResponse::new)
                .collect(Collectors.toList());
    }

    public String getNumeroDaNota() {
        return numeroDaNota;
    }

    public List<ItemResponse> getItens() {
        return itens;
    }

    public String getNomeComprador() {
        return nomeComprador;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

}
