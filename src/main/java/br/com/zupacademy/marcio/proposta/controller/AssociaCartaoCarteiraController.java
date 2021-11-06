package br.com.zupacademy.marcio.proposta.controller;

import br.com.zupacademy.marcio.proposta.commons.errors.exceptions.CartaoInexistenteException;
import br.com.zupacademy.marcio.proposta.commons.errors.exceptions.CartaoJaAssociadaACarteiraException;
import br.com.zupacademy.marcio.proposta.commons.utils.ConsultaCartao;
import br.com.zupacademy.marcio.proposta.dto.CarteiraDto;
import br.com.zupacademy.marcio.proposta.dto.RespostaAssoiciaCartaoCarteiraDto;
import br.com.zupacademy.marcio.proposta.dto.SolicitaAssociaCartaoCarteiraDto;
import br.com.zupacademy.marcio.proposta.entities.Cartao;
import br.com.zupacademy.marcio.proposta.entities.Carteira;
import br.com.zupacademy.marcio.proposta.repository.CartaoRepository;
import br.com.zupacademy.marcio.proposta.repository.CarteiraRepository;
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
@RequestMapping("carteiras")
public class AssociaCartaoCarteiraController {

    @Autowired
    private CarteiraRepository carteiraRepository;

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private ConsultaCartao consultaCartao;

    @PostMapping(value = "/{id}")
    @Transactional
    public ResponseEntity<?> cadastraAssociacaoCartaoCarteira(@PathVariable(name = "id") Long id,
                                                              @RequestBody @Valid CarteiraDto carteiraDto,
                                                              UriComponentsBuilder uriComponentsBuilder
                                                              ) {
        Cartao cartao = cartaoRepository.findById(id).orElseThrow(CartaoInexistenteException::new);

        Optional<Carteira> carteira = carteiraRepository.findByCartaoAndNomeCarteiraDigital(cartao, carteiraDto.getNomeCarteiraDigital());

        if (carteira.isPresent()){
            throw new CartaoJaAssociadaACarteiraException();
        }

        associaCartaoCarteira(carteiraDto, cartao);

        Carteira carteiraAssociada = carteiraDto.toModel(cartao);

        carteiraRepository.save(carteiraAssociada);

        URI endereco = uriComponentsBuilder.path("/carteiras/{id}").build(carteiraAssociada.getId());
        return ResponseEntity.created(endereco).build();
    }

    private void associaCartaoCarteira(CarteiraDto carteiraDto, Cartao cartao) {
        try {
            RespostaAssoiciaCartaoCarteiraDto resposta = consultaCartao.solicitaAssociacaoCartaoCarteira(
                    new SolicitaAssociaCartaoCarteiraDto(cartao.getProposta().getEmail(),carteiraDto.getNomeCarteiraDigital().toString()),
                    cartao.getNumero());

        } catch (FeignException.UnprocessableEntity e) {
            throw new IllegalArgumentException();
        }
    }
}
