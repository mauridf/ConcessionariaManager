package com.concessionariamanager.application.veiculo.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class VeiculoDTO {

    private UUID id;
    private String tipoVeiculo;
    private String marca;
    private String modelo;
    private String versao;
    private int anoFabricacao;
    private int anoModelo;
    private String cor;
    private String combustivel;
    private String cambio;
    private String categoria;
    private BigDecimal preco;
    private String statusVeiculo;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;

    public VeiculoDTO() {}

    public VeiculoDTO(UUID id, String tipoVeiculo, String marca, String modelo, String versao,
                      int anoFabricacao, int anoModelo, String cor, String combustivel,
                      String cambio, String categoria, BigDecimal preco, String statusVeiculo,
                      LocalDateTime dataCriacao, LocalDateTime dataAtualizacao) {
        this.id = id;
        this.tipoVeiculo = tipoVeiculo;
        this.marca = marca;
        this.modelo = modelo;
        this.versao = versao;
        this.anoFabricacao = anoFabricacao;
        this.anoModelo = anoModelo;
        this.cor = cor;
        this.combustivel = combustivel;
        this.cambio = cambio;
        this.categoria = categoria;
        this.preco = preco;
        this.statusVeiculo = statusVeiculo;
        this.dataCriacao = dataCriacao;
        this.dataAtualizacao = dataAtualizacao;
    }

    // Getters e Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getTipoVeiculo() { return tipoVeiculo; }
    public void setTipoVeiculo(String tipoVeiculo) { this.tipoVeiculo = tipoVeiculo; }

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public String getVersao() { return versao; }
    public void setVersao(String versao) { this.versao = versao; }

    public int getAnoFabricacao() { return anoFabricacao; }
    public void setAnoFabricacao(int anoFabricacao) { this.anoFabricacao = anoFabricacao; }

    public int getAnoModelo() { return anoModelo; }
    public void setAnoModelo(int anoModelo) { this.anoModelo = anoModelo; }

    public String getCor() { return cor; }
    public void setCor(String cor) { this.cor = cor; }

    public String getCombustivel() { return combustivel; }
    public void setCombustivel(String combustivel) { this.combustivel = combustivel; }

    public String getCambio() { return cambio; }
    public void setCambio(String cambio) { this.cambio = cambio; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public BigDecimal getPreco() { return preco; }
    public void setPreco(BigDecimal preco) { this.preco = preco; }

    public String getStatusVeiculo() { return statusVeiculo; }
    public void setStatusVeiculo(String statusVeiculo) { this.statusVeiculo = statusVeiculo; }

    public LocalDateTime getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(LocalDateTime dataCriacao) { this.dataCriacao = dataCriacao; }

    public LocalDateTime getDataAtualizacao() { return dataAtualizacao; }
    public void setDataAtualizacao(LocalDateTime dataAtualizacao) { this.dataAtualizacao = dataAtualizacao; }
}
