package br.com.zupacademy.marcio.proposta.commons.utils;

import br.com.zupacademy.marcio.proposta.dto.RetornoAnalisePropostaDto;
import br.com.zupacademy.marcio.proposta.dto.SolicitaAnalisePropostaDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "consultaSeElegivelOuNao", url="localhost:9999/api/solicitacao")
public interface ConsultaElegivel {

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    RetornoAnalisePropostaDto consultaElegibilidade(@RequestBody SolicitaAnalisePropostaDto dto);
}
