package com.concessionariamanager.application.mapper;

import com.concessionariamanager.application.autopecas.dto.AutoPecaDTO;
import com.concessionariamanager.domain.autopeca.AutoPeca;
import com.concessionariamanager.domain.veiculo.Veiculo;

public class AutoPecaMapper {

    public static AutoPecaDTO toDTO(AutoPeca entity) {
        if (entity == null) return null;
        AutoPecaDTO dto = new AutoPecaDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setQuantidade(entity.getQuantidade());
        dto.setValor(entity.getValor());
        dto.setAno(entity.getAno());
        dto.setModelo(entity.getModelo());
        if (entity.getVeiculo() != null) {
            dto.setVeiculoId(entity.getVeiculo().getId());
        }
        return dto;
    }

    public static AutoPeca toEntity(AutoPecaDTO dto, Veiculo veiculo) {
        if (dto == null) return null;
        AutoPeca entity = new AutoPeca();
        entity.setId(dto.getId());
        entity.setNome(dto.getNome());
        entity.setQuantidade(dto.getQuantidade());
        entity.setValor(dto.getValor());
        entity.setAno(dto.getAno());
        entity.setModelo(dto.getModelo());
        entity.setVeiculo(veiculo);
        return entity;
    }
}

