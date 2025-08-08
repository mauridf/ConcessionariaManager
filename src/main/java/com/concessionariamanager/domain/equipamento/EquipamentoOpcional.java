package com.concessionariamanager.domain.equipamento;

import jakarta.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "equipamentos_opcionais")
public class EquipamentoOpcional {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "veiculo_id", nullable = false)
    private com.concessionariamanager.domain.veiculo.Veiculo veiculo;

    @Column(name = "tem_ar_condicionado", nullable = false)
    private boolean temArCondicionado;

    @Column(name = "tem_direcao_hidraulica", nullable = false)
    private boolean temDirecaoHidraulica;

    @Column(name = "tem_airbag", nullable = false)
    private boolean temAirbag;

    @Column(name = "tem_freio_abs", nullable = false)
    private boolean temFreioABS;

    @Column(name = "tem_multimidia", nullable = false)
    private boolean temMultimidia;

    @OneToMany(mappedBy = "equipamentoOpcional", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Opcional> opcionais;

    // Getters e setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public com.concessionariamanager.domain.veiculo.Veiculo getVeiculo() { return veiculo; }
    public void setVeiculo(com.concessionariamanager.domain.veiculo.Veiculo veiculo) { this.veiculo = veiculo; }

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

    public List<Opcional> getOpcionais() { return opcionais; }
    public void setOpcionais(List<Opcional> opcionais) { this.opcionais = opcionais; }
}
