package com.concessionariamanager.application.revisao.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class RevisaoDTO {

    private UUID id;
    private UUID veiculoId;
    private UUID mecanicoId;
    private LocalDateTime dataRevisao;
    private int quilometragemAtual;
    private String descricao;
    private BigDecimal valorTotal;
    private List<ItemRevisaoDTO> itens;

    // getters e setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public UUID getVeiculoId() { return veiculoId; }
    public void setVeiculoId(UUID veiculoId) { this.veiculoId = veiculoId; }

    public UUID getMecanicoId() { return mecanicoId; }
    public void setMecanicoId(UUID mecanicoId) { this.mecanicoId = mecanicoId; }

    public LocalDateTime getDataRevisao() { return dataRevisao; }
    public void setDataRevisao(LocalDateTime dataRevisao) { this.dataRevisao = dataRevisao; }

    public int getQuilometragemAtual() { return quilometragemAtual; }
    public void setQuilometragemAtual(int quilometragemAtual) { this.quilometragemAtual = quilometragemAtual; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public BigDecimal getValorTotal() { return valorTotal; }
    public void setValorTotal(BigDecimal valorTotal) { this.valorTotal = valorTotal; }

    public List<ItemRevisaoDTO> getItens() { return itens; }
    public void setItens(List<ItemRevisaoDTO> itens) { this.itens = itens; }
}
