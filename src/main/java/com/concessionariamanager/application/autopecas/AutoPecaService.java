package com.concessionariamanager.application.autopecas;

import com.concessionariamanager.application.autopecas.dto.AutoPecaDTO;
import com.concessionariamanager.application.mapper.AutoPecaMapper;
import com.concessionariamanager.domain.autopeca.AutoPeca;
import com.concessionariamanager.domain.veiculo.Veiculo;
import com.concessionariamanager.infrastructure.autopecas.AutoPecaRepository;
import com.concessionariamanager.infrastructure.veiculo.VeiculoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AutoPecaService {

    private final AutoPecaRepository autoPecaRepository;
    private final VeiculoRepository veiculoRepository;

    public AutoPecaService(AutoPecaRepository autoPecaRepository, VeiculoRepository veiculoRepository) {
        this.autoPecaRepository = autoPecaRepository;
        this.veiculoRepository = veiculoRepository;
    }

    public AutoPecaDTO salvar(AutoPecaDTO dto) {
        Veiculo veiculo = veiculoRepository.findById(dto.getVeiculoId())
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado para a peça"));

        AutoPeca entity = AutoPecaMapper.toEntity(dto, veiculo);
        AutoPeca salvo = autoPecaRepository.save(entity);
        return AutoPecaMapper.toDTO(salvo);
    }

    public List<AutoPecaDTO> listarTodos() {
        return autoPecaRepository.findAll()
                .stream()
                .map(AutoPecaMapper::toDTO)
                .collect(Collectors.toList());
    }

    public AutoPecaDTO buscarPorId(UUID id) {
        AutoPeca entity = autoPecaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Auto-peça não encontrada"));
        return AutoPecaMapper.toDTO(entity);
    }

    public AutoPecaDTO atualizar(UUID id, AutoPecaDTO dto) {
        AutoPeca entityExistente = autoPecaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Auto-peça não encontrada"));

        Veiculo veiculo = veiculoRepository.findById(dto.getVeiculoId())
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado para a peça"));

        entityExistente.setNome(dto.getNome());
        entityExistente.setQuantidade(dto.getQuantidade());
        entityExistente.setValor(dto.getValor());
        entityExistente.setAno(dto.getAno());
        entityExistente.setModelo(dto.getModelo());
        entityExistente.setVeiculo(veiculo);

        AutoPeca atualizado = autoPecaRepository.save(entityExistente);
        return AutoPecaMapper.toDTO(atualizado);
    }

    public void deletar(UUID id) {
        AutoPeca entity = autoPecaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Auto-peça não encontrada"));
        autoPecaRepository.delete(entity);
    }
}
