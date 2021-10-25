package br.com.zupacademy.marcio.proposta.repository;

import br.com.zupacademy.marcio.proposta.entities.Proposta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PropostaRepository extends JpaRepository<Proposta, Long> {
    Optional<Proposta> findBycpfcnpj(String cpfcnpj);
}
