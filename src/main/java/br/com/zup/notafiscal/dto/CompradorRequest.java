package br.com.zup.notafiscal.dto;

import java.time.LocalDate;

public class CompradorRequest {
    private String nome;
    private String cpf;
    private String email;
    private String endereco;
    private LocalDate dataNascimento;

    public CompradorRequest() {
    }

    public CompradorRequest(String nome, String cpf, String email, String endereco, LocalDate dataNascimento) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.endereco = endereco;
        this.dataNascimento = dataNascimento;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public String getEndereco() {
        return endereco;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }
}