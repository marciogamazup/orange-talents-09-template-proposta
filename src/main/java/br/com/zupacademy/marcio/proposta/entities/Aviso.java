package br.com.zupacademy.marcio.proposta.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_aviso")
public class Aviso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String destino;
    @Column(nullable = false)
    private String ipcliente;
    @Column(nullable = false)
    private String useragent;

    private LocalDateTime dataaviso = LocalDateTime.now();
    @Column(nullable = false)
    private LocalDateTime validoate;
    @ManyToOne
    @JoinColumn(name = "cartao_id", nullable = false)
    private Cartao cartao;

    @Deprecated
    public Aviso() {
    }

    public Aviso(String destino, String ipcliente, String useragent, LocalDateTime validoate, Cartao cartao) {
        this.destino = destino;
        this.ipcliente = ipcliente;
        this.useragent = useragent;
        this.dataaviso = dataaviso;
        this.validoate = validoate;
        this.cartao = cartao;
    }

    public Long getId() {
        return id;
    }
}
