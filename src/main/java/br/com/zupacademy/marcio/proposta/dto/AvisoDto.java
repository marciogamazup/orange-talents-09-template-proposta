package br.com.zupacademy.marcio.proposta.dto;

import br.com.zupacademy.marcio.proposta.entities.Aviso;
import br.com.zupacademy.marcio.proposta.entities.Cartao;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class AvisoDto {

    @NotBlank
    private String destino;
    @NotNull
    @FutureOrPresent
    private LocalDate validoAte;

    @Deprecated
    public AvisoDto() {
    }

    public AvisoDto(String destino, LocalDate validoAte) {
        this.destino = destino;
        this.validoAte = validoAte;
    }

    public String getDestino() {
        return destino;
    }

    public LocalDate getValidoAte() {
        return validoAte;
    }

    public Aviso toModel(String ipcliente, String useragent, Cartao cartao) {
        return new Aviso(destino, ipcliente, useragent, validoAte, cartao);
    }
}