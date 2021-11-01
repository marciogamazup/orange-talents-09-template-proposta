package br.com.zupacademy.marcio.proposta.dto;

import br.com.zupacademy.marcio.proposta.entities.Biometria;
import br.com.zupacademy.marcio.proposta.entities.Cartao;

import javax.validation.constraints.NotBlank;

public class BiometriaDto {

    @NotBlank
    private String fingerPrint;

    @Deprecated
    public BiometriaDto() {
    }

    public BiometriaDto(String fingerPrint) {
        this.fingerPrint = fingerPrint;
    }

    public String getFingerPrint() {
        return fingerPrint;
    }

    public Biometria toModel(Cartao cartao) {
        return new Biometria(fingerPrint, cartao);
    }
}
