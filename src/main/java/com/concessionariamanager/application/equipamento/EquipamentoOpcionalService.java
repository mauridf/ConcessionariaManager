package com.concessionariamanager.application.equipamento;

import com.concessionariamanager.application.equipamento.dto.EquipamentoOpcionalDTO;
import com.concessionariamanager.application.mapper.EquipamentoOpcionalMapper;
import com.concessionariamanager.application.mapper.OpcionalMapper;
import com.concessionariamanager.domain.equipamento.EquipamentoOpcional;
import com.concessionariamanager.domain.veiculo.Veiculo;
import com.concessionariamanager.infrastructure.equipamento.EquipamentoOpcionalRepository;
import com.concessionariamanager.infrastructure.veiculo.VeiculoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EquipamentoOpcionalService {

    private final EquipamentoOpcionalRepository equipamentoOpcionalRepository;
    private final VeiculoRepository veiculoRepository;

    public EquipamentoOpcionalService(EquipamentoOpcionalRepository equipamentoOpcionalRepository,
                                      VeiculoRepository veiculoRepository) {
        this.equipamentoOpcionalRepository = equipamentoOpcionalRepository;
        this.veiculoRepository = veiculoRepository;
    }

    public EquipamentoOpcionalDTO salvar(EquipamentoOpcionalDTO dto) {
        Veiculo veiculo = veiculoRepository.findById(dto.getVeiculoId())
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado"));

        EquipamentoOpcional entity = EquipamentoOpcionalMapper.toEntity(dto, veiculo);
        EquipamentoOpcional salvo = equipamentoOpcionalRepository.save(entity);
        return EquipamentoOpcionalMapper.toDTO(salvo);
    }

    public List<EquipamentoOpcionalDTO> listarTodos() {
        return equipamentoOpcionalRepository.findAll()
                .stream()
                .map(EquipamentoOpcionalMapper::toDTO)
                .collect(Collectors.toList());
    }

    public EquipamentoOpcionalDTO buscarPorId(UUID id) {
        EquipamentoOpcional entity = equipamentoOpcionalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Equipamento/Opcional não encontrado"));
        return EquipamentoOpcionalMapper.toDTO(entity);
    }

    public EquipamentoOpcionalDTO atualizar(UUID id, EquipamentoOpcionalDTO dto) {
        EquipamentoOpcional existente = equipamentoOpcionalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Equipamento/Opcional não encontrado"));

        Veiculo veiculo = veiculoRepository.findById(dto.getVeiculoId())
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado"));

        existente.setTemArCondicionado(dto.isTemArCondicionado());
        existente.setTemDirecaoHidraulica(dto.isTemDirecaoHidraulica());
        existente.setTemAirbag(dto.isTemAirbag());
        existente.setTemFreioABS(dto.isTemFreioABS());
        existente.setTemMultimidia(dto.isTemMultimidia());
        existente.setVeiculo(veiculo);

        // Atualizar lista de opcionais:
        existente.getOpcionais().clear();
        existente.getOpcionais().addAll(dto.getOpcionais().stream()
                .map(o -> OpcionalMapper.toEntity(o, existente))
                .toList());

        EquipamentoOpcional atualizado = equipamentoOpcionalRepository.save(existente);
        return EquipamentoOpcionalMapper.toDTO(atualizado);
    }

    public void deletar(UUID id) {
        EquipamentoOpcional entity = equipamentoOpcionalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Equipamento/Opcional não encontrado"));
        equipamentoOpcionalRepository.delete(entity);
    }
}

