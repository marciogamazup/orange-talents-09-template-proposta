package br.com.zupacademy.marcio.proposta.dto;

public class RespostaAvisaViagemDto {

    private String resultado;

    @Deprecated
    public RespostaAvisaViagemDto() {
    }

    public RespostaAvisaViagemDto(String resultado) {
        this.resultado = resultado;
    }

    public String getResultado() {
        return resultado;
    }
}
