package br.com.zupacademy.marcio.proposta.controller;

import br.com.zupacademy.marcio.proposta.dto.PropostaDto;
import br.com.zupacademy.marcio.proposta.entities.Proposta;
import br.com.zupacademy.marcio.proposta.repository.PropostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/propostas")
public class PropostaController {

    @Autowired
    PropostaRepository propostaRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid PropostaDto dto,
                                       UriComponentsBuilder uriComponentsBuilder) {
        Proposta proposta = dto.toModel(propostaRepository);
        propostaRepository.save(proposta);

        URI endereco = uriComponentsBuilder.path("/propostas/{id}").build(proposta.getId());
        return ResponseEntity.created(endereco).build();
    }
}
