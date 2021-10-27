package br.com.zupacademy.marcio.proposta.commons.errors.exceptions;

import feign.FeignException;

public class FeignExceptionNaoElegivel extends FeignException {

    public FeignExceptionNaoElegivel(int status, String message, Throwable cause) {
        super(status, message, cause);
    }
}
