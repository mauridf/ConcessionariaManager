package com.concessionariamanager.application.mapper;

import com.concessionariamanager.application.equipamento.dto.EquipamentoOpcionalDTO;
import com.concessionariamanager.application.equipamento.dto.OpcionalDTO;
import com.concessionariamanager.domain.equipamento.EquipamentoOpcional;
import com.concessionariamanager.domain.equipamento.Opcional;
import com.concessionariamanager.domain.veiculo.Veiculo;

import java.util.List;
import java.util.stream.Collectors;

public class EquipamentoOpcionalMapper {

    public static EquipamentoOpcionalDTO toDTO(EquipamentoOpcional entity) {
        if (entity == null) return null;

        EquipamentoOpcionalDTO dto = new EquipamentoOpcionalDTO();
        dto.setId(entity.getId());
        if (entity.getVeiculo() != null) {
            dto.setVeiculoId(entity.getVeiculo().getId());
        }
        dto.setTemArCondicionado(entity.isTemArCondicionado());
        dto.setTemDirecaoHidraulica(entity.isTemDirecaoHidraulica());
        dto.setTemAirbag(entity.isTemAirbag());
        dto.setTemFreioABS(entity.isTemFreioABS());
        dto.setTemMultimidia(entity.isTemMultimidia());

        List<OpcionalDTO> opcionaisDTO = entity.getOpcionais() == null ? List.of() :
                entity.getOpcionais().stream()
                        .map(OpcionalMapper::toDTO)
                        .collect(Collectors.toList());
        dto.setOpcionais(opcionaisDTO);

        return dto;
    }

    public static EquipamentoOpcional toEntity(EquipamentoOpcionalDTO dto, Veiculo veiculo) {
        if (dto == null) return null;

        EquipamentoOpcional entity = new EquipamentoOpcional();
        entity.setId(dto.getId());
        entity.setVeiculo(veiculo);
        entity.setTemArCondicionado(dto.isTemArCondicionado());
        entity.setTemDirecaoHidraulica(dto.isTemDirecaoHidraulica());
        entity.setTemAirbag(dto.isTemAirbag());
        entity.setTemFreioABS(dto.isTemFreioABS());
        entity.setTemMultimidia(dto.isTemMultimidia());

        List<Opcional> opcionais = dto.getOpcionais() == null ? List.of() :
                dto.getOpcionais().stream()
                        .map(o -> OpcionalMapper.toEntity(o, entity))
                        .collect(Collectors.toList());
        entity.setOpcionais(opcionais);

        return entity;
    }
}

