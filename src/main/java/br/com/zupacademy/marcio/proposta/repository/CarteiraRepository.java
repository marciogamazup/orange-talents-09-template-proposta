package br.com.zupacademy.marcio.proposta.repository;

import br.com.zupacademy.marcio.proposta.entities.Cartao;
import br.com.zupacademy.marcio.proposta.entities.Carteira;
import br.com.zupacademy.marcio.proposta.entities.enums.NomeCarteiraDigital;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarteiraRepository extends JpaRepository<Carteira,Long> {
    Optional<Carteira> findByCartaoAndNomeCarteiraDigital(Cartao cartao, NomeCarteiraDigital nomeCarteiraDigital);
}
