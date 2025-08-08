package com.concessionariamanager.domain.veiculo;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "dados_tecnicos_veiculo")
public class DadosTecnicosVeiculo {

    @Id
    @GeneratedValue
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "veiculo_id", nullable = false, unique = true)
    private Veiculo veiculo;

    @Column(name = "renavam", nullable = false)
    private String renavam;

    @Column(name = "chassi", nullable = false)
    private String chassi;

    @Column(name = "placa", nullable = false)
    private String placa;

    @Column(name = "quilometragem", nullable = false)
    private int quilometragem;

    @Column(name = "portas", nullable = false)
    private int portas;

    @Column(name = "potencia_motor", nullable = false)
    private String potenciaMotor;

    @Column(name = "motor", nullable = false)
    private String motor;

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

    public String getRenavam() {
        return renavam;
    }

    public void setRenavam(String renavam) {
        this.renavam = renavam;
    }

    public String getChassi() {
        return chassi;
    }

    public void setChassi(String chassi) {
        this.chassi = chassi;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public int getQuilometragem() {
        return quilometragem;
    }

    public void setQuilometragem(int quilometragem) {
        this.quilometragem = quilometragem;
    }

    public int getPortas() {
        return portas;
    }

    public void setPortas(int portas) {
        this.portas = portas;
    }

    public String getPotenciaMotor() {
        return potenciaMotor;
    }

    public void setPotenciaMotor(String potenciaMotor) {
        this.potenciaMotor = potenciaMotor;
    }

    public String getMotor() {
        return motor;
    }

    public void setMotor(String motor) {
        this.motor = motor;
    }
}
