package br.com.zupacademy.marcio.proposta.dto;

import br.com.zupacademy.marcio.proposta.commons.validators.CpfCnpj;
import br.com.zupacademy.marcio.proposta.entities.Proposta;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class PropostaDto {

    @NotBlank
    @CpfCnpj
    private String cpfcnpj;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String nome;

    @NotBlank
    private String endereco;

    @NotNull
    @Positive
    private BigDecimal salario;

    @Deprecated
    public PropostaDto(){
    }

    public PropostaDto(String cpfcnpj, String email, String nome, String endereco, BigDecimal salario) {
        this.cpfcnpj = cpfcnpj;
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
    }

    public String getCpfcnpj() {
        return cpfcnpj;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public Proposta toModel() {
        return new Proposta(cpfcnpj, email, nome, endereco, salario);
    }
}
