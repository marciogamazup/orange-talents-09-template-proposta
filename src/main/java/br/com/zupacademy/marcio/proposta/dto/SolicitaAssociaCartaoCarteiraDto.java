package br.com.zupacademy.marcio.proposta.dto;

public class SolicitaAssociaCartaoCarteiraDto {

    private String email;
    private String carteira;

    @Deprecated
    public SolicitaAssociaCartaoCarteiraDto() {
    }

    public SolicitaAssociaCartaoCarteiraDto(String email, String carteira) {
        this.email = email;
        this.carteira = carteira;
    }

    public String getEmail() {
        return email;
    }

    public String getCarteira() {
        return carteira;
    }
}
