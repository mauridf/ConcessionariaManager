package com.concessionariamanager.application.veiculo;

import com.concessionariamanager.domain.veiculo.DadosTecnicosVeiculo;
import com.concessionariamanager.domain.veiculo.Veiculo;
import com.concessionariamanager.infrastructure.veiculo.DadosTecnicosVeiculoRepository;
import com.concessionariamanager.infrastructure.veiculo.VeiculoRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DadosTecnicosVeiculoService {

    private final DadosTecnicosVeiculoRepository dadosTecnicosVeiculoRepository;
    private final VeiculoRepository veiculoRepository;

    public DadosTecnicosVeiculoService(DadosTecnicosVeiculoRepository dadosTecnicosVeiculoRepository,
                                       VeiculoRepository veiculoRepository) {
        this.dadosTecnicosVeiculoRepository = dadosTecnicosVeiculoRepository;
        this.veiculoRepository = veiculoRepository;
    }

    public DadosTecnicosVeiculo salvar(DadosTecnicosVeiculo dadosTecnicos) {
        // Validação: Veículo deve existir
        Veiculo veiculo = veiculoRepository.findById(dadosTecnicos.getVeiculo().getId())
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado para dados técnicos"));
        dadosTecnicos.setVeiculo(veiculo);
        return dadosTecnicosVeiculoRepository.save(dadosTecnicos);
    }

    public DadosTecnicosVeiculo buscarPorId(UUID id) {
        return dadosTecnicosVeiculoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dados técnicos do veículo não encontrados"));
    }

    public DadosTecnicosVeiculo buscarPorVeiculoId(UUID veiculoId) {
        return dadosTecnicosVeiculoRepository.findByVeiculoId(veiculoId)
                .orElseThrow(() -> new RuntimeException("Dados técnicos para o veículo não encontrados"));
    }

    public DadosTecnicosVeiculo atualizar(UUID id, DadosTecnicosVeiculo dadosAtualizados) {
        DadosTecnicosVeiculo existente = buscarPorId(id);
        existente.setRenavam(dadosAtualizados.getRenavam());
        existente.setChassi(dadosAtualizados.getChassi());
        existente.setPlaca(dadosAtualizados.getPlaca());
        existente.setQuilometragem(dadosAtualizados.getQuilometragem());
        existente.setPortas(dadosAtualizados.getPortas());
        existente.setPotenciaMotor(dadosAtualizados.getPotenciaMotor());
        existente.setMotor(dadosAtualizados.getMotor());

        return dadosTecnicosVeiculoRepository.save(existente);
    }

    public void deletar(UUID id) {
        DadosTecnicosVeiculo existente = buscarPorId(id);
        dadosTecnicosVeiculoRepository.delete(existente);
    }
}
