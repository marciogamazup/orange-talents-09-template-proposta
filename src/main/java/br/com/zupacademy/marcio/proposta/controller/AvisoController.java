package br.com.zupacademy.marcio.proposta.controller;

import br.com.zupacademy.marcio.proposta.commons.errors.exceptions.CartaoInexistenteException;
import br.com.zupacademy.marcio.proposta.dto.AvisoDto;
import br.com.zupacademy.marcio.proposta.entities.Aviso;
import br.com.zupacademy.marcio.proposta.entities.Cartao;
import br.com.zupacademy.marcio.proposta.repository.AvisoRepository;
import br.com.zupacademy.marcio.proposta.repository.CartaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("avisos")
public class AvisoController {

    @Autowired
    private AvisoRepository avisoRepository;

    @Autowired
    private CartaoRepository cartaoRepository;

    @PostMapping(value = "/{id}")
    @Transactional
    public ResponseEntity<?> cadastraAviso(@PathVariable(name = "id") Long id,
                                           @RequestHeader(value = "User-Agent") String userAgent,
                                           HttpServletRequest request,
                                           @RequestBody @Valid AvisoDto avisoDto,
                                           UriComponentsBuilder uriComponentsBuilder
                                           ) {

        Cartao cartao = cartaoRepository.findById(id).orElseThrow(CartaoInexistenteException::new);

        Aviso aviso = avisoDto.toModel(request.getRemoteAddr(),userAgent,cartao);

        avisoRepository.save(aviso);

        URI endereco = uriComponentsBuilder.path("/aviso/{id}").build(aviso.getId());

        return ResponseEntity.ok(endereco);
    }
}
