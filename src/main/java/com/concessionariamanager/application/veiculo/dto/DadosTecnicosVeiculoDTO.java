package com.concessionariamanager.application.veiculo.dto;

import java.util.UUID;

public class DadosTecnicosVeiculoDTO {

    private UUID id;
    private UUID veiculoId;
    private String renavam;
    private String chassi;
    private String placa;
    private int quilometragem;
    private int portas;
    private String potenciaMotor;
    private String motor;

    // Getters e setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public UUID getVeiculoId() { return veiculoId; }
    public void setVeiculoId(UUID veiculoId) { this.veiculoId = veiculoId; }

    public String getRenavam() { return renavam; }
    public void setRenavam(String renavam) { this.renavam = renavam; }

    public String getChassi() { return chassi; }
    public void setChassi(String chassi) { this.chassi = chassi; }

    public String getPlaca() { return placa; }
    public void setPlaca(String placa) { this.placa = placa; }

    public int getQuilometragem() { return quilometragem; }
    public void setQuilometragem(int quilometragem) { this.quilometragem = quilometragem; }

    public int getPortas() { return portas; }
    public void setPortas(int portas) { this.portas = portas; }

    public String getPotenciaMotor() { return potenciaMotor; }
    public void setPotenciaMotor(String potenciaMotor) { this.potenciaMotor = potenciaMotor; }

    public String getMotor() { return motor; }
    public void setMotor(String motor) { this.motor = motor; }
}
