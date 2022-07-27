package br.com.zup.notafiscal.dto;

public class PagamentoRequest {
    private String id;
    private String forma;
    private String status;

    public PagamentoRequest() {
    }

    public PagamentoRequest(String id, String forma, String status) {
        this.id = id;
        this.forma = forma;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getForma() {
        return forma;
    }

    public String getStatus() {
        return status;
    }

}
