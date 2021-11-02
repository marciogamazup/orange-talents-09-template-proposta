package br.com.zupacademy.marcio.proposta.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_bloqueio")
public class Bloqueio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private LocalDateTime bloqueadoEm = LocalDateTime.now();
    @Column(nullable = false)
    private String ipcliente;
    @Column(nullable = false)
    private String useragent;
    @Column(nullable = false)
    private String numero;
    @ManyToOne
    @JoinColumn(name = "cartao_id", nullable = false)
    private Cartao cartao;

    @Deprecated
    public Bloqueio() {
    }

    public Bloqueio(String ipcliente, String useragent, String numero, Cartao cartao) {
        this.ipcliente = ipcliente;
        this.useragent = useragent;
        this.numero = numero;
        this.cartao = cartao;
    }
}
