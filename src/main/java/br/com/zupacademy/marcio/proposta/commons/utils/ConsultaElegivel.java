package br.com.zupacademy.marcio.proposta.commons.utils;

import br.com.zupacademy.marcio.proposta.dto.RetornoAnliseDto;
import br.com.zupacademy.marcio.proposta.dto.SolicitaAnaliseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "consultaSeElegivelOuNao", url="localhost:9999/api/solicitacao")
public interface ConsultaElegivel {

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    RetornoAnliseDto consultaElegibilidade(@RequestBody SolicitaAnaliseDto dto);
}
