package br.com.zupacademy.marcio.proposta.repository;

import br.com.zupacademy.marcio.proposta.entities.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartaoRepository extends JpaRepository<Cartao, Long> {

    Optional<Cartao> findByNumero(String numero);
}
