package com.concessionariamanager.domain.veiculo;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "veiculos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Veiculo {

    @Id
    @GeneratedValue
    private UUID id;

    @Enumerated(EnumType.STRING)
    private TipoVeiculo tipoVeiculo;

    private String marca;

    private String modelo;

    private String versao;

    private int anoFabricacao;

    private int anoModelo;

    private String cor;

    @Enumerated(EnumType.STRING)
    private Combustivel combustivel;

    @Enumerated(EnumType.STRING)
    private Cambio cambio;

    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    private BigDecimal preco;

    @Enumerated(EnumType.STRING)
    private StatusVeiculo statusVeiculo;

    @Column(name = "foto", length = 255)
    private String foto;

    @CreationTimestamp
    private LocalDateTime dataCriacao;

    @UpdateTimestamp
    private LocalDateTime dataAtualizacao;
}
