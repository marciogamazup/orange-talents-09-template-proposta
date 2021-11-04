package br.com.zupacademy.marcio.proposta.dto;

import br.com.zupacademy.marcio.proposta.entities.Aviso;
import br.com.zupacademy.marcio.proposta.entities.Cartao;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class AvisoDto {

    @NotBlank
    private String destino;
    @NotNull
    @FutureOrPresent
    private LocalDateTime validoate;

    @Deprecated
    public AvisoDto() {
    }

    public AvisoDto(String destino, LocalDateTime validoate) {
        this.destino = destino;
        this.validoate = validoate;
    }

    public String getDestino() {
        return destino;
    }

    public LocalDateTime getValidoate() {
        return validoate;
    }

    public Aviso toModel(String ipcliente, String useragent, Cartao cartao) {
        return new Aviso(destino, ipcliente, useragent, validoate, cartao);
    }
}