package br.com.zupacademy.marcio.proposta.entities;

import br.com.zupacademy.marcio.proposta.commons.errors.exceptions.Base64IllegalException;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Base64;

@Entity
@Table(name = "tb_biometria")
public class  Biometria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fingerPrint;

    private LocalDateTime associadaEm = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "cartao_id", nullable = false)
    private Cartao cartao;

    @Deprecated
    public Biometria() {
    }

    public Biometria(String fingerPrint, Cartao cartao) {
        this.fingerPrint = fingerPrint;
        this.cartao = cartao;
    }

    public Long getId() {
        return id;
    }

    public void biometriaBase64Valida(String fingerPrint) {

        Base64.Decoder decoder = Base64.getDecoder();

        try {
            decoder.decode(fingerPrint);
        } catch (IllegalArgumentException e) {
            throw new Base64IllegalException();
        }
    }
}

// Exemplos de base64

// Exemplo1  => RXhlbXBsbzE=
// Tratamento => VHJhdGFtZW50bw==
// Verificado => VmVyaWZpY2FkYQ==