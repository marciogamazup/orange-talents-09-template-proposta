package br.com.zupacademy.marcio.proposta.commons.errors;

import br.com.zupacademy.marcio.proposta.commons.errors.exceptions.Base64IllegalException;
import br.com.zupacademy.marcio.proposta.commons.errors.exceptions.CartaoInexistenteException;
import br.com.zupacademy.marcio.proposta.commons.errors.exceptions.ExceptionCpfCnpjJaExiste;
import br.com.zupacademy.marcio.proposta.commons.errors.exceptions.FeignExceptionNaoElegivel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ErroDeValidacaoHandler {

    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ErroDeFormularioDto> handle(MethodArgumentNotValidException exception) {

        List<ErroDeFormularioDto> dto = new ArrayList<>();
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

        fieldErrors.forEach( e -> {
            String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
            ErroDeFormularioDto erro = new ErroDeFormularioDto(e.getField(),mensagem);
            dto.add(erro);
        });
        return dto;
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleAnyException(Exception e, WebRequest request) {

        String fieldDescription = e.getMessage();
        String errorDescription = e.getLocalizedMessage();
        if(errorDescription == null) errorDescription = e.toString();

        ErroDeFormularioDto erroDeFormularioDto = new ErroDeFormularioDto(fieldDescription,errorDescription);
        return new ResponseEntity<>(erroDeFormularioDto, new HttpHeaders(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {ExceptionCpfCnpjJaExiste.class})
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErroDeFormularioDto CpfCnpjJaExisteException(ExceptionCpfCnpjJaExiste e, WebRequest request) {

        ErroDeFormularioDto erroDeFormularioDto = new ErroDeFormularioDto("CPF/CNPJ","CPF e/ou CNPJ já está cadastrado em nossa base !");

        return  erroDeFormularioDto;
    }

    @ExceptionHandler(value = {FeignExceptionNaoElegivel.class})
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErroDeFormularioDto ConsultaNaoElegivel(FeignExceptionNaoElegivel e, WebRequest request) {

        ErroDeFormularioDto erroDeFormularioDto = new ErroDeFormularioDto("CPF/CNPJ","CPF e/ou CNPJ com restrições financeiras !");

        return  erroDeFormularioDto;
    }

    @ExceptionHandler(value = {CartaoInexistenteException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErroDeFormularioDto HandleCartaoNaoExisteException(CartaoInexistenteException e, WebRequest request) {

        ErroDeFormularioDto erroDeFormularioDto = new ErroDeFormularioDto("No. Cartão","Número de Cartão não existe  !");

        return  erroDeFormularioDto;
    }

    @ExceptionHandler(value = {Base64IllegalException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErroDeFormularioDto HandleBase64IllegalException(Base64IllegalException e, WebRequest request) {

        ErroDeFormularioDto erroDeFormularioDto = new ErroDeFormularioDto("FingerPrint","Valor do FingerPrint não está na Base64 !");

        return  erroDeFormularioDto;
    }

}
