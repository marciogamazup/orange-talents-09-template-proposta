package br.com.zupacademy.marcio.proposta.controller;

import br.com.zupacademy.marcio.proposta.commons.errors.exceptions.CartaoInexistenteException;
import br.com.zupacademy.marcio.proposta.commons.errors.exceptions.CartaoJaBloqueadoException;
import br.com.zupacademy.marcio.proposta.entities.Bloqueio;
import br.com.zupacademy.marcio.proposta.entities.Cartao;
import br.com.zupacademy.marcio.proposta.repository.BloqueioRepository;
import br.com.zupacademy.marcio.proposta.repository.CartaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("bloqueios")
public class BloqueioController {

    @Autowired
    private BloqueioRepository bloqueioRepository;

    @Autowired
    private CartaoRepository cartaoRepository;

    @PostMapping(value = "/{id}")
    @Transactional
    public ResponseEntity<?> cadastraBloqueio(@PathVariable(name = "id") Long id, @RequestHeader(value = "User-Agent") String userAgent,
                                              HttpServletRequest request) {
        Cartao cartao = cartaoRepository.findById(id).orElseThrow(CartaoInexistenteException::new);
        Bloqueio bloqueio = new Bloqueio(request.getRemoteAddr(), userAgent, cartao.getNumero(), cartao);

        if (!(bloqueioRepository.findByNumero(cartao.getNumero()).isPresent())){
            bloqueioRepository.save(bloqueio);
            return ResponseEntity.ok().build();
        } else {
            throw new CartaoJaBloqueadoException();
        }
    }
}
