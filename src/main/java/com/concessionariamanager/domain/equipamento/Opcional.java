package com.concessionariamanager.domain.equipamento;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "opcionais")
public class Opcional {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equipamento_opcional_id", nullable = false)
    private EquipamentoOpcional equipamentoOpcional;

    @Column(nullable = false)
    private String opcional;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal valorOpcional;

    // Getters e setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public EquipamentoOpcional getEquipamentoOpcional() { return equipamentoOpcional; }
    public void setEquipamentoOpcional(EquipamentoOpcional equipamentoOpcional) { this.equipamentoOpcional = equipamentoOpcional; }

    public String getOpcional() { return opcional; }
    public void setOpcional(String opcional) { this.opcional = opcional; }

    public BigDecimal getValorOpcional() { return valorOpcional; }
    public void setValorOpcional(BigDecimal valorOpcional) { this.valorOpcional = valorOpcional; }
}
