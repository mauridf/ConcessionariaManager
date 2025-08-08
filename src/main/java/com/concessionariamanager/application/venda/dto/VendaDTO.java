package com.concessionariamanager.application.venda.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class VendaDTO {

    private UUID id;
    private UUID clienteId;
    private UUID veiculoId;
    private UUID vendedorId;
    private UUID equipamentosOpcionaisId;

    private BigDecimal valorCompra;
    private BigDecimal valorDescontos;
    private BigDecimal valorFinal;

    private LocalDateTime dataVenda;

    // getters e setters

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public UUID getClienteId() { return clienteId; }
    public void setClienteId(UUID clienteId) { this.clienteId = clienteId; }

    public UUID getVeiculoId() { return veiculoId; }
    public void setVeiculoId(UUID veiculoId) { this.veiculoId = veiculoId; }

    public UUID getVendedorId() { return vendedorId; }
    public void setVendedorId(UUID vendedorId) { this.vendedorId = vendedorId; }

    public UUID getEquipamentosOpcionaisId() { return equipamentosOpcionaisId; }
    public void setEquipamentosOpcionaisId(UUID equipamentosOpcionaisId) { this.equipamentosOpcionaisId = equipamentosOpcionaisId; }

    public BigDecimal getValorCompra() { return valorCompra; }
    public void setValorCompra(BigDecimal valorCompra) { this.valorCompra = valorCompra; }

    public BigDecimal getValorDescontos() { return valorDescontos; }
    public void setValorDescontos(BigDecimal valorDescontos) { this.valorDescontos = valorDescontos; }

    public BigDecimal getValorFinal() { return valorFinal; }
    public void setValorFinal(BigDecimal valorFinal) { this.valorFinal = valorFinal; }

    public LocalDateTime getDataVenda() { return dataVenda; }
    public void setDataVenda(LocalDateTime dataVenda) { this.dataVenda = dataVenda; }

    public void setClienteId(Long aLong) {
    }
}
