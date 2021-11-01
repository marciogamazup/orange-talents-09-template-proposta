package br.com.zupacademy.marcio.proposta.commons.errors.exceptions;

public class Base64IllegalException extends RuntimeException{
    public Base64IllegalException(){
        super("Biometria não está na Base64 !");
    }
}
