package br.com.zupacademy.marcio.proposta.entities;

import br.com.zupacademy.marcio.proposta.entities.enums.Status;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_cartao")
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime emitidoEm;

    @Column(nullable = false)
    private String titular;

    @Column(nullable = false)
    private Integer limite;

    @Column(nullable = false)
    private String numero;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToOne(mappedBy = "cartao")
    private Proposta proposta;

    @Deprecated
    public Cartao() {
    }

    public Cartao(LocalDateTime emitidoEm, String titular, Integer limite, String numero, Proposta proposta) {
        this.emitidoEm = emitidoEm;
        this.titular = titular;
        this.limite = limite;
        this.numero = numero;
        this.proposta = proposta;
    }

    public String getNumero() {
        return numero;
    }

    public void BloqueiaCartao(){
        this.status = Status.BLOQUEADO;
    }
}
