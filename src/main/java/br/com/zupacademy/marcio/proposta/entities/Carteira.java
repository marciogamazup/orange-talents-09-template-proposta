package br.com.zupacademy.marcio.proposta.entities;

import br.com.zupacademy.marcio.proposta.entities.enums.NomeCarteiraDigital;

import javax.persistence.*;

@Entity
@Table(name = "tb_carteira")
public class Carteira {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String carteiraId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private NomeCarteiraDigital nomeCarteiraDigital;

    @ManyToOne
    @JoinColumn(name = "cartao_id", nullable = false)
    private Cartao cartao;

    @Deprecated
    public Carteira() {
    }

    public Carteira(String carteiraId, NomeCarteiraDigital nomeCarteiraDigital, Cartao cartao) {
        this.carteiraId = carteiraId;
        this.nomeCarteiraDigital = nomeCarteiraDigital;
        this.cartao = cartao;
    }

    public long getId() {
        return id;
    }

    public NomeCarteiraDigital getNomeCarteiraDigital() {
        return nomeCarteiraDigital;
    }
}
