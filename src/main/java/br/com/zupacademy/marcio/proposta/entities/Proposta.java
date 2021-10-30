package br.com.zupacademy.marcio.proposta.entities;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "tb_proposta")
public class Proposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String cpfcnpj;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String endereco;

    @Column(nullable = false)
    private BigDecimal salario;

    @Enumerated(EnumType.STRING)
    private StatusElegivel statusElegivel;

    @OneToOne(cascade = CascadeType.ALL)
    private Cartao cartao;

    @Deprecated
    public Proposta(){
    }

    public Proposta(String cpfcnpj, String email, String nome, String endereco, BigDecimal salario) {
        this.cpfcnpj = cpfcnpj;
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
    }

    public long getId() {
        return id;
    }

    public void avaliaRetornoElegibilidade(String retornoElegivel) {
            this.statusElegivel = StatusElegivel.ELEGIVEL;
    }

    public void gravaNaoElegivel(Proposta proposta) {
        this.statusElegivel = StatusElegivel.NAO_ELEGIVEL;
    }

    public void associaCartao(Cartao cartao) {
        this.cartao = cartao;
    }
}
