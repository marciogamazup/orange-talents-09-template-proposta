package br.com.zupacademy.marcio.proposta.commons.errors.exceptions;

public class CartaoInexistenteException extends RuntimeException{
    public CartaoInexistenteException(){
        super("Cartão Inexistente !");
    }
}
