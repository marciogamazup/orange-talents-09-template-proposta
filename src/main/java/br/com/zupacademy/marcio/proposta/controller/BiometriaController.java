package br.com.zupacademy.marcio.proposta.controller;

import br.com.zupacademy.marcio.proposta.commons.errors.exceptions.CartaoInexistenteException;
import br.com.zupacademy.marcio.proposta.dto.BiometriaDto;
import br.com.zupacademy.marcio.proposta.entities.Biometria;
import br.com.zupacademy.marcio.proposta.entities.Cartao;
import br.com.zupacademy.marcio.proposta.repository.BiometriaRepository;
import br.com.zupacademy.marcio.proposta.repository.CartaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/biometria")
public class BiometriaController {

    @Autowired
    private BiometriaRepository biometriaRepository;


    @Autowired
    private CartaoRepository cartaoRepository;

    @PostMapping("/{id}")
    @Transactional
    public ResponseEntity<BiometriaDto> cadastrar(@PathVariable(name = "id") Long id,
                                                  @RequestBody @Valid BiometriaDto dto,
                                                  UriComponentsBuilder uriComponentsBuilder){
        Cartao cartao = cartaoRepository.findById(id).orElseThrow(CartaoInexistenteException::new);

        Biometria biometria = dto.toModel(cartao);
        biometria.biometriaBase64Valida(dto.getFingerPrint());
        biometriaRepository.save(biometria);

        URI endereco = uriComponentsBuilder.path("/biometria/{id}").build(biometria.getId());
        return ResponseEntity.created(endereco).build();
    }
}
