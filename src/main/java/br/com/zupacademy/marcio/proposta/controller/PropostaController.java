package br.com.zupacademy.marcio.proposta.controller;

import br.com.zupacademy.marcio.proposta.commons.utils.ConsultaElegivel;
import br.com.zupacademy.marcio.proposta.dto.ConsultaStatusPropostaDto;
import br.com.zupacademy.marcio.proposta.dto.PropostaDto;
import br.com.zupacademy.marcio.proposta.dto.RetornoAnalisePropostaDto;
import br.com.zupacademy.marcio.proposta.dto.SolicitaAnalisePropostaDto;
import br.com.zupacademy.marcio.proposta.entities.Proposta;
import br.com.zupacademy.marcio.proposta.repository.PropostaRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/propostas")
public class PropostaController {

    @Autowired
    PropostaRepository propostaRepository;

    @Autowired
    ConsultaElegivel consultaElegivel;

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid PropostaDto dto,
                                       UriComponentsBuilder uriComponentsBuilder) {

        Proposta proposta = dto.toModel(propostaRepository);
        propostaRepository.save(proposta);

        SolicitaAnalisePropostaDto solicitaAnaliseCartaoDto = new SolicitaAnalisePropostaDto(dto.getCpfcnpj(), dto.getNome(),
                                                                        Long.toString(proposta.getId())) ;

        try {
            RetornoAnalisePropostaDto retornoAnliseDto = consultaElegivel.consultaElegibilidade(solicitaAnaliseCartaoDto);
            proposta.avaliaRetornoElegibilidade(retornoAnliseDto.getResultadoSolicitacao());
        } catch (FeignException.UnprocessableEntity exception) {
            proposta.gravaNaoElegivel(proposta);
            propostaRepository.save(proposta);
        }

        URI endereco = uriComponentsBuilder.path("/propostas/{id}").build(proposta.getId());
        return ResponseEntity.created(endereco).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultaStatusPropostaDto> acharUmaProposta(@PathVariable Long id) {

        Optional<Proposta> propostaPorId = propostaRepository.findById(id);

        if (propostaPorId.isPresent()) {

            ConsultaStatusPropostaDto consultaPropostaPorId = new ConsultaStatusPropostaDto(propostaPorId.get());
            return ResponseEntity.ok(consultaPropostaPorId);
        }
        return ResponseEntity.notFound().build();
    }



}
