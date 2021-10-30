package br.com.zupacademy.marcio.proposta.dto;

public class SolicitaAnalisePropostaDto {

    private String documento;
    private String nome;
    private String idProposta;

    @Deprecated
    public SolicitaAnalisePropostaDto() {
    }

    public SolicitaAnalisePropostaDto(String documento, String nome, String idProposta) {
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
