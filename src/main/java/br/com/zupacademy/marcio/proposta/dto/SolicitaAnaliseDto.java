package br.com.zupacademy.marcio.proposta.dto;

public class SolicitaAnaliseDto {

    private String documento;
    private String nome;
    private String idProposta;

    @Deprecated
    public SolicitaAnaliseDto() {
    }

    public SolicitaAnaliseDto(String documento, String nome, String idProposta) {
        this.documento = documento;
        this.nome = nome;
        this.idProposta = idProposta;
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public String getIdProposta() {
        return idProposta;
    }
}
