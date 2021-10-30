package br.com.zupacademy.marcio.proposta.repository;

import br.com.zupacademy.marcio.proposta.entities.Cartao;
import br.com.zupacademy.marcio.proposta.entities.Proposta;
import br.com.zupacademy.marcio.proposta.entities.StatusElegivel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PropostaRepository extends JpaRepository<Proposta, Long> {

    Optional<Proposta> findBycpfcnpj(String cpfcnpj);

    List<Proposta> findByStatusElegivelAndCartao(StatusElegivel status, Cartao cartao);
}
