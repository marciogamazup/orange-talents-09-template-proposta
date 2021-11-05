package br.com.zupacademy.marcio.proposta.dto;

public class SolicitaAvisoViagemDto {

    private String validoAte;
    private String destino;

    @Deprecated
    public SolicitaAvisoViagemDto() {
    }

    public SolicitaAvisoViagemDto(String validoAte, String destino) {
        this.validoAte = validoAte;
        this.destino = destino;
    }

    public String getValidoAte() {
        return validoAte;
    }

    public String getDestino() {
        return destino;
    }
}
