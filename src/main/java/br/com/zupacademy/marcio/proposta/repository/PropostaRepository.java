package br.com.zupacademy.marcio.proposta.repository;

import br.com.zupacademy.marcio.proposta.entities.Proposta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropostaRepository extends JpaRepository<Proposta, Long> {
}
