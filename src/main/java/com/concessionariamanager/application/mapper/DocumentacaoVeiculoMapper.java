package com.concessionariamanager.application.mapper;

import com.concessionariamanager.application.veiculo.dto.DocumentacaoVeiculoDTO;
import com.concessionariamanager.domain.veiculo.DocumentacaoVeiculo;
import com.concessionariamanager.domain.veiculo.Veiculo;
import com.concessionariamanager.domain.veiculo.DocumentacaoVeiculo.Origem;

public class DocumentacaoVeiculoMapper {

    public static DocumentacaoVeiculoDTO toDTO(DocumentacaoVeiculo entity) {
        if (entity == null) return null;

        DocumentacaoVeiculoDTO dto = new DocumentacaoVeiculoDTO();
        dto.setId(entity.getId());
        dto.setVeiculoId(entity.getVeiculo() != null ? entity.getVeiculo().getId() : null);
        dto.setPossuiIPVA(entity.isPossuiIPVA());
        dto.setLicenciamentoOK(entity.isLicenciamentoOK());
        dto.setMultasPendentes(entity.isMultasPendentes());
        dto.setValorMultas(entity.getValorMultas());
        dto.setOrigem(entity.getOrigem() != null ? entity.getOrigem().name() : null);

        return dto;
    }

    public static DocumentacaoVeiculo toEntity(DocumentacaoVeiculoDTO dto, Veiculo veiculo) {
        if (dto == null) return null;

        DocumentacaoVeiculo entity = new DocumentacaoVeiculo();
        entity.setId(dto.getId());
        entity.setVeiculo(veiculo);
        entity.setPossuiIPVA(dto.isPossuiIPVA());
        entity.setLicenciamentoOK(dto.isLicenciamentoOK());
        entity.setMultasPendentes(dto.isMultasPendentes());
        entity.setValorMultas(dto.getValorMultas());

        if (dto.getOrigem() != null) {
            try {
                entity.setOrigem(Origem.valueOf(dto.getOrigem()));
            } catch (IllegalArgumentException e) {
                entity.setOrigem(null); // ou tratar erro
            }
        } else {
            entity.setOrigem(null);
        }

        return entity;
    }
}
