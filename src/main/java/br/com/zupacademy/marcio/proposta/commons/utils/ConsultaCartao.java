package br.com.zupacademy.marcio.proposta.commons.utils;

import br.com.zupacademy.marcio.proposta.dto.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "consultaCartao", url="localhost:8888/api/cartoes")
public interface ConsultaCartao {

    @GetMapping(value = "?idProposta={id}")
    RetornoBuscaCartaoAprovadoDto pegaNumeroCartaoAprovado(@PathVariable long id);

    @PostMapping(value = "/{id}/bloqueios")
    RespostaSolicitaBloqueioCartaoDto solicitaBloqueio(@RequestBody SolicitaBloqueioCartaoDto dto, @PathVariable String id);

    @PostMapping(value = "/{id}/avisos")
    RespostaAvisaViagemDto solicitaAvisoViagem(@RequestBody SolicitaAvisoViagemDto dto, @PathVariable String id);

    @PostMapping(value = "/{id}/carteiras")
    RespostaAssoiciaCartaoCarteiraDto solicitaAssociacaoCartaoCarteira(@RequestBody SolicitaAssociaCartaoCarteiraDto dto, @PathVariable String id);
}
