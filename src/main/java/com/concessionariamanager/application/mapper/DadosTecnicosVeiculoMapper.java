package com.concessionariamanager.application.mapper;

import com.concessionariamanager.application.veiculo.dto.DadosTecnicosVeiculoDTO;
import com.concessionariamanager.domain.veiculo.DadosTecnicosVeiculo;
import com.concessionariamanager.domain.veiculo.Veiculo;

public class DadosTecnicosVeiculoMapper {

    public static DadosTecnicosVeiculoDTO toDTO(DadosTecnicosVeiculo entity) {
        if (entity == null) return null;

        DadosTecnicosVeiculoDTO dto = new DadosTecnicosVeiculoDTO();
        dto.setId(entity.getId());
        dto.setVeiculoId(entity.getVeiculo() != null ? entity.getVeiculo().getId() : null);
        dto.setRenavam(entity.getRenavam());
        dto.setChassi(entity.getChassi());
        dto.setPlaca(entity.getPlaca());
        dto.setQuilometragem(entity.getQuilometragem());
        dto.setPortas(entity.getPortas());
        dto.setPotenciaMotor(entity.getPotenciaMotor());
        dto.setMotor(entity.getMotor());

        return dto;
    }

    public static DadosTecnicosVeiculo toEntity(DadosTecnicosVeiculoDTO dto, Veiculo veiculo) {
        if (dto == null) return null;

        DadosTecnicosVeiculo entity = new DadosTecnicosVeiculo();
        entity.setId(dto.getId());
        entity.setVeiculo(veiculo);
        entity.setRenavam(dto.getRenavam());
        entity.setChassi(dto.getChassi());
        entity.setPlaca(dto.getPlaca());
        entity.setQuilometragem(dto.getQuilometragem());
        entity.setPortas(dto.getPortas());
        entity.setPotenciaMotor(dto.getPotenciaMotor());
        entity.setMotor(dto.getMotor());

        return entity;
    }
}
