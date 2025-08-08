package com.concessionariamanager.domain.revisao;

import com.concessionariamanager.domain.veiculo.Veiculo;
import com.concessionariamanager.domain.user.User;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "revisoes")
public class Revisao {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "veiculo_id", nullable = false)
    private Veiculo veiculo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mecanico_id", nullable = false)
    private User mecanico;

    @Column(name = "data_revisao", nullable = false)
    private LocalDateTime dataRevisao;

    @Column(name = "quilometragem_atual", nullable = false)
    private int quilometragemAtual;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Column(name = "valor_total", nullable = false, precision = 10, scale = 2)
    private BigDecimal valorTotal;

    @OneToMany(mappedBy = "revisao", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemRevisao> itens;

    // getters e setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public Veiculo getVeiculo() { return veiculo; }
    public void setVeiculo(Veiculo veiculo) { this.veiculo = veiculo; }

    public User getMecanico() { return mecanico; }
    public void setMecanico(User mecanico) { this.mecanico = mecanico; }

    public LocalDateTime getDataRevisao() { return dataRevisao; }
    public void setDataRevisao(LocalDateTime dataRevisao) { this.dataRevisao = dataRevisao; }

    public int getQuilometragemAtual() { return quilometragemAtual; }
    public void setQuilometragemAtual(int quilometragemAtual) { this.quilometragemAtual = quilometragemAtual; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public BigDecimal getValorTotal() { return valorTotal; }
    public void setValorTotal(BigDecimal valorTotal) { this.valorTotal = valorTotal; }

    public List<ItemRevisao> getItens() { return itens; }
    public void setItens(List<ItemRevisao> itens) { this.itens = itens; }
}
