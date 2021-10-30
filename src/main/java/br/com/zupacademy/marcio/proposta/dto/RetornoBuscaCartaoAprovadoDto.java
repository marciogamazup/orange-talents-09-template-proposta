package br.com.zupacademy.marcio.proposta.dto;

import br.com.zupacademy.marcio.proposta.entities.Cartao;
import br.com.zupacademy.marcio.proposta.entities.Proposta;

import java.time.LocalDateTime;

public class RetornoBuscaCartaoAprovadoDto {

    private String id;

    private LocalDateTime emitidoEm;

    private String titular;

    private Integer limite;

    @Deprecated
    public RetornoBuscaCartaoAprovadoDto() {
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getEmitidoEm() {
        return emitidoEm;
    }

    public String getTitular() {
        return titular;
    }

    public Integer getLimite() {
        return limite;
    }

    public Cartao toModel(Proposta proposta){
        return new Cartao(emitidoEm, titular, limite, id, proposta);
    }
}