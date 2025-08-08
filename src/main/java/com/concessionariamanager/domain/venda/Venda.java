package com.concessionariamanager.domain.venda;

import com.concessionariamanager.domain.cliente.Cliente;
import com.concessionariamanager.domain.veiculo.Veiculo;
import com.concessionariamanager.domain.equipamento.EquipamentoOpcional;
import com.concessionariamanager.domain.user.User;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "vendas")
public class Venda {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "veiculo_id", nullable = false)
    private Veiculo veiculo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendedor_id", nullable = false)
    private User vendedor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equipamentos_opcionais_id")
    private EquipamentoOpcional equipamentosOpcionais;

    @Column(name = "valor_compra", nullable = false, precision = 10, scale = 2)
    private BigDecimal valorCompra;

    @Column(name = "valor_descontos", nullable = false, precision = 10, scale = 2)
    private BigDecimal valorDescontos;

    @Column(name = "valor_final", nullable = false, precision = 10, scale = 2)
    private BigDecimal valorFinal;

    @Column(name = "data_venda", nullable = false)
    private LocalDateTime dataVenda;

    // getters e setters

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public Veiculo getVeiculo() { return veiculo; }
    public void setVeiculo(Veiculo veiculo) { this.veiculo = veiculo; }

    public User getVendedor() { return vendedor; }
    public void setVendedor(User vendedor) { this.vendedor = vendedor; }

    public EquipamentoOpcional getEquipamentosOpcionais() { return equipamentosOpcionais; }
    public void setEquipamentosOpcionais(EquipamentoOpcional equipamentosOpcionais) { this.equipamentosOpcionais = equipamentosOpcionais; }

    public BigDecimal getValorCompra() { return valorCompra; }
    public void setValorCompra(BigDecimal valorCompra) { this.valorCompra = valorCompra; }

    public BigDecimal getValorDescontos() { return valorDescontos; }
    public void setValorDescontos(BigDecimal valorDescontos) { this.valorDescontos = valorDescontos; }

    public BigDecimal getValorFinal() { return valorFinal; }
    public void setValorFinal(BigDecimal valorFinal) { this.valorFinal = valorFinal; }

    public LocalDateTime getDataVenda() { return dataVenda; }
    public void setDataVenda(LocalDateTime dataVenda) { this.dataVenda = dataVenda; }
}
