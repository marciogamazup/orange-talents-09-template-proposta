package br.com.zupacademy.marcio.proposta.dto;

import br.com.zupacademy.marcio.proposta.entities.Cartao;
import br.com.zupacademy.marcio.proposta.entities.Carteira;
import br.com.zupacademy.marcio.proposta.entities.enums.NomeCarteiraDigital;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CarteiraDto {

    @NotBlank
    @Email
    private String email;

    @NotNull
    private NomeCarteiraDigital nomeCarteiraDigital;

    @Deprecated
    public CarteiraDto() {
    }

    public String getEmail() {
        return email;
    }

    public NomeCarteiraDigital getNomeCarteiraDigital() {
        return nomeCarteiraDigital;
    }

    public Carteira toModel(Cartao cartao) {
        return new Carteira(email, nomeCarteiraDigital, cartao);
    }
}
