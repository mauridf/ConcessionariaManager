package com.concessionariamanager.application.equipamento.dto;

import java.math.BigDecimal;
import java.util.UUID;

public class OpcionalDTO {

    private UUID id;
    private UUID equipamentoOpcionalId;
    private String opcional;
    private BigDecimal valorOpcional;

    // getters e setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public UUID getEquipamentoOpcionalId() { return equipamentoOpcionalId; }
    public void setEquipamentoOpcionalId(UUID equipamentoOpcionalId) { this.equipamentoOpcionalId = equipamentoOpcionalId; }

    public String getOpcional() { return opcional; }
    public void setOpcional(String opcional) { this.opcional = opcional; }

    public BigDecimal getValorOpcional() { return valorOpcional; }
    public void setValorOpcional(BigDecimal valorOpcional) { this.valorOpcional = valorOpcional; }
}

