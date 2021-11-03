package br.com.zupacademy.marcio.proposta.controller;

import br.com.zupacademy.marcio.proposta.commons.utils.ConsultaCartao;
import br.com.zupacademy.marcio.proposta.dto.RetornoBuscaCartaoAprovadoDto;
import br.com.zupacademy.marcio.proposta.entities.Cartao;
import br.com.zupacademy.marcio.proposta.entities.Proposta;
import br.com.zupacademy.marcio.proposta.entities.enums.StatusElegivel;
import br.com.zupacademy.marcio.proposta.repository.PropostaRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AssociaCartaoAPropostaScheduler {

    @Autowired
    private PropostaRepository propostaRepository;

    @Autowired
    private ConsultaCartao consultaCartao;

    @Scheduled(fixedDelayString = "${periodicidade.executa-operacao}")
    public void associa() {

         List<Proposta> propostasElegiveis = propostaRepository.findByStatusElegivelAndCartao(StatusElegivel.ELEGIVEL, null);

         for(Proposta proposta : propostasElegiveis) {
             try {
                 RetornoBuscaCartaoAprovadoDto retorno = consultaCartao.pegaNumeroCartaoAprovado(proposta.getId());
                 Cartao cartao = retorno.toModel(proposta);
                 proposta.associaCartao(cartao);
                 propostaRepository.save(proposta);
             } catch (FeignException.GatewayTimeout e){
                 return;
             }
         }
    }
}
