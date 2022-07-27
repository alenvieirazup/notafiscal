package br.com.zup.notafiscal.model;

import br.com.zup.notafiscal.model.Item;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static java.time.LocalDateTime.now;

@Entity
public class NotaFiscal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "nota_id")
    private List<Item> itens;
    private String nomeComprador;
    private String cpf;
    private String endereco;

    private String email;
    private BigDecimal valorTotal;
    private String status = "GERADA";
    private LocalDateTime criadoEm = now();

    public NotaFiscal() {
    }

    public NotaFiscal(List<Item> itens, String nomeComprador, String cpf, String endereco,
                      String email, BigDecimal valorTotal) {
        this.itens = itens;
        this.nomeComprador = nomeComprador;
        this.cpf = cpf;
        this.endereco = endereco;
        this.email = email;
        this.valorTotal = valorTotal;
    }

    public Long getId() {
        return id;
    }

    public String getNumeroNota() {
        return String.format("%05d", id);
    }

    public List<Item> getItens() {
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

    public String getEmail() {
        return email;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
