package com.concessionariamanager.application.revisao;

import com.concessionariamanager.domain.revisao.Revisao;
import com.concessionariamanager.domain.user.Role;
import com.concessionariamanager.domain.user.User;
import com.concessionariamanager.domain.veiculo.Veiculo;
import com.concessionariamanager.infrastructure.revisao.RevisaoRepository;
import com.concessionariamanager.infrastructure.veiculo.VeiculoRepository;
import com.concessionariamanager.domain.user.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class RevisaoService {

    private final RevisaoRepository revisaoRepository;
    private final VeiculoRepository veiculoRepository;
    private final UserRepository usuarioRepository;

    public RevisaoService(RevisaoRepository revisaoRepository,
                          VeiculoRepository veiculoRepository,
                          UserRepository usuarioRepository) {
        this.revisaoRepository = revisaoRepository;
        this.veiculoRepository = veiculoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<Revisao> listarTodos() {
        return revisaoRepository.findAll();
    }

    public Revisao buscarPorId(UUID id) {
        return revisaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Revisão não encontrada"));
    }

    @Transactional
    public Revisao salvar(Revisao revisao) {
        // Verificar se veículo existe
        Veiculo veiculo = veiculoRepository.findById(revisao.getVeiculo().getId())
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado"));

        // Verificar se mecânico existe e tem role "MECANICO"
        User mecanico = usuarioRepository.findById(revisao.getMecanico().getId())
                .orElseThrow(() -> new RuntimeException("Mecânico não encontrado"));

        if (mecanico.getRole() != Role.MECANICO) {
            throw new RuntimeException("Usuário não é um mecânico válido");
        }

        revisao.setVeiculo(veiculo);
        revisao.setMecanico(mecanico);

        return revisaoRepository.save(revisao);
    }

    @Transactional
    public Revisao atualizar(UUID id, Revisao revisaoAtualizada) {
        Revisao revisaoExistente = buscarPorId(id);

        // Atualizar campos relevantes
        revisaoExistente.setDataRevisao(revisaoAtualizada.getDataRevisao());
        revisaoExistente.setQuilometragemAtual(revisaoAtualizada.getQuilometragemAtual());
        revisaoExistente.setDescricao(revisaoAtualizada.getDescricao());
        revisaoExistente.setValorTotal(revisaoAtualizada.getValorTotal());

        // Atualizar itens: removendo antigos e adicionando os novos
        revisaoExistente.getItens().clear();
        revisaoExistente.getItens().addAll(revisaoAtualizada.getItens());

        return revisaoRepository.save(revisaoExistente);
    }

    public void deletar(UUID id) {
        Revisao revisao = buscarPorId(id);
        revisaoRepository.delete(revisao);
    }
}
