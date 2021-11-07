package br.com.zupacademy.marcio.proposta.dto;

import br.com.zupacademy.marcio.proposta.commons.errors.exceptions.ExceptionCpfCnpjJaExiste;
import br.com.zupacademy.marcio.proposta.commons.security.CryptoConverter;
import br.com.zupacademy.marcio.proposta.commons.validators.CpfCnpj;
import br.com.zupacademy.marcio.proposta.entities.Proposta;
import br.com.zupacademy.marcio.proposta.repository.PropostaRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.persistence.Convert;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.Optional;

public class PropostaDto {

    @NotBlank
    @CpfCnpj
    @Convert(converter = CryptoConverter.class)
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

    public Proposta toModel(PropostaRepository propostaRepository) {

        Optional<Proposta> proposta = propostaRepository.findBycpfcnpj(cpfcnpj);

        if(proposta.isPresent()) {
            throw new ExceptionCpfCnpjJaExiste();
        }

        return new Proposta(cpfcnpj, email, nome, endereco, salario);
    }
}
