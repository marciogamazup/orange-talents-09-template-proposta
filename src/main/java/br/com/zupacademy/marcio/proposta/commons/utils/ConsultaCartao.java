package br.com.zupacademy.marcio.proposta.commons.utils;

import br.com.zupacademy.marcio.proposta.dto.RetornoBuscaCartaoAprovadoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "consultaCartao", url="localhost:8888/api/cartoes")
public interface ConsultaCartao {

    @GetMapping(value = "?idProposta={id}")
    RetornoBuscaCartaoAprovadoDto pegaNumeroCartaoAprovado(@PathVariable long id);
}
