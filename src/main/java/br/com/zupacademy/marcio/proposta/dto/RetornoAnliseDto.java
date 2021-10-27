package br.com.zupacademy.marcio.proposta.dto;

public class RetornoAnliseDto {

    private String documento;
    private String nome;
    private String resultadoSolicitacao;
    private String idProposta;

    @Deprecated
    public RetornoAnliseDto() {
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public String getResultadoSolicitacao() {
        return resultadoSolicitacao;
    }

    public String getIdProposta() {
        return idProposta;
    }
}
