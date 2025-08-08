package com.concessionariamanager.application.veiculo.dto;

import java.math.BigDecimal;
import java.util.UUID;

public class DocumentacaoVeiculoDTO {

    private UUID id;
    private UUID veiculoId;
    private boolean possuiIPVA;
    private boolean licenciamentoOK;
    private boolean multasPendentes;
    private BigDecimal valorMultas;
    private String origem; // Podemos mapear enum como string

    // Getters e setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public UUID getVeiculoId() { return veiculoId; }
    public void setVeiculoId(UUID veiculoId) { this.veiculoId = veiculoId; }

    public boolean isPossuiIPVA() { return possuiIPVA; }
    public void setPossuiIPVA(boolean possuiIPVA) { this.possuiIPVA = possuiIPVA; }

    public boolean isLicenciamentoOK() { return licenciamentoOK; }
    public void setLicenciamentoOK(boolean licenciamentoOK) { this.licenciamentoOK = licenciamentoOK; }

    public boolean isMultasPendentes() { return multasPendentes; }
    public void setMultasPendentes(boolean multasPendentes) { this.multasPendentes = multasPendentes; }

    public BigDecimal getValorMultas() { return valorMultas; }
    public void setValorMultas(BigDecimal valorMultas) { this.valorMultas = valorMultas; }

    public String getOrigem() { return origem; }
    public void setOrigem(String origem) { this.origem = origem; }
}
