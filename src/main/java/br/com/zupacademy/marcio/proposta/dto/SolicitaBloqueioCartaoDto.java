package br.com.zupacademy.marcio.proposta.dto;

public class SolicitaBloqueioCartaoDto {

    private String sistemaResponsavel;

    @Deprecated
    public SolicitaBloqueioCartaoDto() {
    }

    public SolicitaBloqueioCartaoDto(String sistemaResponsavel) {
        this.sistemaResponsavel = sistemaResponsavel;
    }

    public String getSistemaResponsavel() {
        return sistemaResponsavel;
    }
}
