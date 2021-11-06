package br.com.zupacademy.marcio.proposta.commons.errors.exceptions;

public class CartaoJaAssociadaACarteiraException extends RuntimeException{
    public CartaoJaAssociadaACarteiraException(){
        super("Cartao já Associado a esssa Carteira !");
    }
}
