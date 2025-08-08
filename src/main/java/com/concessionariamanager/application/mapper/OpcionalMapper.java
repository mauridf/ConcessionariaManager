package com.concessionariamanager.application.mapper;

import com.concessionariamanager.application.equipamento.dto.OpcionalDTO;
import com.concessionariamanager.domain.equipamento.Opcional;
import com.concessionariamanager.domain.equipamento.EquipamentoOpcional;

public class OpcionalMapper {

    public static OpcionalDTO toDTO(Opcional entity) {
        if (entity == null) return null;
        OpcionalDTO dto = new OpcionalDTO();
        dto.setId(entity.getId());
        if (entity.getEquipamentoOpcional() != null) {
            dto.setEquipamentoOpcionalId(entity.getEquipamentoOpcional().getId());
        }
        dto.setOpcional(entity.getOpcional());
        dto.setValorOpcional(entity.getValorOpcional());
        return dto;
    }

    public static Opcional toEntity(OpcionalDTO dto, EquipamentoOpcional equipamentoOpcional) {
        if (dto == null) return null;
        Opcional entity = new Opcional();
        entity.setId(dto.getId());
        entity.setEquipamentoOpcional(equipamentoOpcional);
        entity.setOpcional(dto.getOpcional());
        entity.setValorOpcional(dto.getValorOpcional());
        return entity;
    }
}

