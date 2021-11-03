package br.com.zupacademy.marcio.proposta.dto;

import br.com.zupacademy.marcio.proposta.entities.Proposta;
import br.com.zupacademy.marcio.proposta.entities.enums.StatusElegivel;

public class ConsultaStatusPropostaDto {

    private String cpfcnpj;
    private String email;
    private String nome;
    private StatusElegivel statusElegivel;

    @Deprecated
    public ConsultaStatusPropostaDto(){
    }

    public ConsultaStatusPropostaDto(Proposta proposta) {
        this.cpfcnpj = proposta.getCpfcnpj();
        this.email = proposta.getEmail();
        this.nome = proposta.getNome();
        this.statusElegivel = proposta.getStatusElegivel();
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

    public StatusElegivel getStatusElegivel() {
        return statusElegivel;
    }
}
