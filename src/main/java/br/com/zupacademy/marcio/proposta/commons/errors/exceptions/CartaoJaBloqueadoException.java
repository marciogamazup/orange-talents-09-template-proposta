package br.com.zupacademy.marcio.proposta.commons.errors.exceptions;

public class CartaoJaBloqueadoException extends RuntimeException{
    public CartaoJaBloqueadoException(){
        super("Cartão já está bloqueado !");
    }
}
