package br.com.zupacademy.marcio.proposta.dto;

public class RespostaAssoiciaCartaoCarteiraDto {

    private String resultado;
    private String id;

    @Deprecated
    public RespostaAssoiciaCartaoCarteiraDto() {
    }

    public String getResultado() {
        return resultado;
    }

    public String getId() {
        return id;
    }
}
