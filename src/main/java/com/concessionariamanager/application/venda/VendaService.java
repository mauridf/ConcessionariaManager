package com.concessionariamanager.application.venda;

import com.concessionariamanager.domain.user.Role;
import com.concessionariamanager.domain.venda.Venda;
import com.concessionariamanager.domain.cliente.Cliente;
import com.concessionariamanager.domain.veiculo.Veiculo;
import com.concessionariamanager.domain.user.User;
import com.concessionariamanager.domain.equipamento.EquipamentoOpcional;
import com.concessionariamanager.infrastructure.venda.VendaRepository;
import com.concessionariamanager.infrastructure.cliente.ClienteRepository;
import com.concessionariamanager.infrastructure.veiculo.VeiculoRepository;
import com.concessionariamanager.domain.user.UserRepository;
import com.concessionariamanager.infrastructure.equipamento.EquipamentoOpcionalRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class VendaService {

    private final VendaRepository vendaRepository;
    private final ClienteRepository clienteRepository;
    private final VeiculoRepository veiculoRepository;
    private final UserRepository usuarioRepository;
    private final EquipamentoOpcionalRepository equipamentosOpcionaisRepository;

    public VendaService(VendaRepository vendaRepository,
                        ClienteRepository clienteRepository,
                        VeiculoRepository veiculoRepository,
                        UserRepository usuarioRepository,
                        EquipamentoOpcionalRepository equipamentosOpcionaisRepository) {
        this.vendaRepository = vendaRepository;
        this.clienteRepository = clienteRepository;
        this.veiculoRepository = veiculoRepository;
        this.usuarioRepository = usuarioRepository;
        this.equipamentosOpcionaisRepository = equipamentosOpcionaisRepository;
    }

    public List<Venda> listarTodos() {
        return vendaRepository.findAll();
    }

    public Venda buscarPorId(UUID id) {
        return vendaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venda não encontrada"));
    }

    @Transactional
    public Venda salvar(Venda venda) {
        // Validar e carregar entidades relacionadas

        Cliente cliente = clienteRepository.findById(venda.getCliente().getId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        Veiculo veiculo = veiculoRepository.findById(venda.getVeiculo().getId())
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado"));

        User vendedor = usuarioRepository.findById(venda.getVendedor().getId())
                .orElseThrow(() -> new RuntimeException("Vendedor não encontrado"));

        if (vendedor.getRole() != Role.VENDEDOR && vendedor.getRole() != Role.GERENTE) {
            throw new RuntimeException("Usuário não tem permissão para realizar vendas");
        }

        EquipamentoOpcional equipamentos = null;
        if (venda.getEquipamentosOpcionais() != null && venda.getEquipamentosOpcionais().getId() != null) {
            equipamentos = equipamentosOpcionaisRepository.findById(venda.getEquipamentosOpcionais().getId())
                    .orElseThrow(() -> new RuntimeException("Equipamentos e opcionais não encontrados"));
        }

        venda.setCliente(cliente);
        venda.setVeiculo(veiculo);
        venda.setVendedor(vendedor);
        venda.setEquipamentosOpcionais(equipamentos);

        return vendaRepository.save(venda);
    }

    @Transactional
    public Venda atualizar(UUID id, Venda vendaAtualizada) {
        Venda vendaExistente = buscarPorId(id);

        vendaExistente.setValorCompra(vendaAtualizada.getValorCompra());
        vendaExistente.setValorDescontos(vendaAtualizada.getValorDescontos());
        vendaExistente.setValorFinal(vendaAtualizada.getValorFinal());
        vendaExistente.setDataVenda(vendaAtualizada.getDataVenda());

        // Atualizar relacionamentos também, se necessário
        vendaExistente.setCliente(vendaAtualizada.getCliente());
        vendaExistente.setVeiculo(vendaAtualizada.getVeiculo());
        vendaExistente.setVendedor(vendaAtualizada.getVendedor());
        vendaExistente.setEquipamentosOpcionais(vendaAtualizada.getEquipamentosOpcionais());

        return vendaRepository.save(vendaExistente);
    }

    public void deletar(UUID id) {
        Venda venda = buscarPorId(id);
        vendaRepository.delete(venda);
    }
}
