package com.concessionariamanager.application.equipamento.dto;

import java.util.List;
import java.util.UUID;

public class EquipamentoOpcionalDTO {

    private UUID id;
    private UUID veiculoId;
    private boolean temArCondicionado;
    private boolean temDirecaoHidraulica;
    private boolean temAirbag;
    private boolean temFreioABS;
    private boolean temMultimidia;
    private List<OpcionalDTO> opcionais;

    // getters e setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public UUID getVeiculoId() { return veiculoId; }
    public void setVeiculoId(UUID veiculoId) { this.veiculoId = veiculoId; }

    public boolean isTemArCondicionado() { return temArCondicionado; }
    public void setTemArCondicionado(boolean temArCondicionado) { this.temArCondicionado = temArCondicionado; }

    public boolean isTemDirecaoHidraulica() { return temDirecaoHidraulica; }
    public void setTemDirecaoHidraulica(boolean temDirecaoHidraulica) { this.temDirecaoHidraulica = temDirecaoHidraulica; }

    public boolean isTemAirbag() { return temAirbag; }
    public void setTemAirbag(boolean temAirbag) { this.temAirbag = temAirbag; }

    public boolean isTemFreioABS() { return temFreioABS; }
    public void setTemFreioABS(boolean temFreioABS) { this.temFreioABS = temFreioABS; }

    public boolean isTemMultimidia() { return temMultimidia; }
    public void setTemMultimidia(boolean temMultimidia) { this.temMultimidia = temMultimidia; }

    public List<OpcionalDTO> getOpcionais() { return opcionais; }
    public void setOpcionais(List<OpcionalDTO> opcionais) { this.opcionais = opcionais; }
}

