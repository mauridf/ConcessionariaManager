package com.concessionariamanager.domain.veiculo;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "documentacao_veiculo")
public class DocumentacaoVeiculo {

    @Id
    @GeneratedValue
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "veiculo_id", nullable = false, unique = true)
    private Veiculo veiculo;

    @Column(name = "possui_ipva", nullable = false)
    private boolean possuiIPVA;

    @Column(name = "licenciamento_ok", nullable = false)
    private boolean licenciamentoOK;

    @Column(name = "multas_pendentes", nullable = false)
    private boolean multasPendentes;

    @Column(name = "valor_multas", nullable = false, precision = 10, scale = 2)
    private BigDecimal valorMultas;

    @Enumerated(EnumType.STRING)
    @Column(name = "origem", nullable = false)
    private Origem origem;

    // Getters e setters

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public boolean isPossuiIPVA() {
        return possuiIPVA;
    }

    public void setPossuiIPVA(boolean possuiIPVA) {
        this.possuiIPVA = possuiIPVA;
    }

    public boolean isLicenciamentoOK() {
        return licenciamentoOK;
    }

    public void setLicenciamentoOK(boolean licenciamentoOK) {
        this.licenciamentoOK = licenciamentoOK;
    }

    public boolean isMultasPendentes() {
        return multasPendentes;
    }

    public void setMultasPendentes(boolean multasPendentes) {
        this.multasPendentes = multasPendentes;
    }

    public BigDecimal getValorMultas() {
        return valorMultas;
    }

    public void setValorMultas(BigDecimal valorMultas) {
        this.valorMultas = valorMultas;
    }

    public Origem getOrigem() {
        return origem;
    }

    public void setOrigem(Origem origem) {
        this.origem = origem;
    }

    // Enum Origem para as opções: TROCA, REPASSE, FORNECEDOR

    public enum Origem {
        TROCA,
        REPASSE,
        FORNECEDOR
    }
}
