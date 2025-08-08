package com.concessionariamanager.application.revisao.dto;

import java.math.BigDecimal;
import java.util.UUID;

public class ItemRevisaoDTO {

    private UUID id;
    private UUID revisaoId;
    private String descricao;
    private int quantidade;
    private BigDecimal valorUnitario;

    // getters e setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public UUID getRevisaoId() { return revisaoId; }
    public void setRevisaoId(UUID revisaoId) { this.revisaoId = revisaoId; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }

    public BigDecimal getValorUnitario() { return valorUnitario; }
    public void setValorUnitario(BigDecimal valorUnitario) { this.valorUnitario = valorUnitario; }
}
