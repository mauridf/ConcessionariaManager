package com.concessionariamanager.application.mapper;

import com.concessionariamanager.application.revisao.dto.ItemRevisaoDTO;
import com.concessionariamanager.domain.revisao.ItemRevisao;
import com.concessionariamanager.domain.revisao.Revisao;

public class ItemRevisaoMapper {

    public static ItemRevisaoDTO toDTO(ItemRevisao entity) {
        if (entity == null) return null;

        ItemRevisaoDTO dto = new ItemRevisaoDTO();
        dto.setId(entity.getId());
        dto.setRevisaoId(entity.getRevisao() != null ? entity.getRevisao().getId() : null);
        dto.setDescricao(entity.getDescricao());
        dto.setQuantidade(entity.getQuantidade());
        dto.setValorUnitario(entity.getValorUnitario());
        return dto;
    }

    public static ItemRevisao toEntity(ItemRevisaoDTO dto, Revisao revisao) {
        if (dto == null) return null;

        ItemRevisao entity = new ItemRevisao();
        entity.setId(dto.getId());
        entity.setRevisao(revisao);
        entity.setDescricao(dto.getDescricao());
        entity.setQuantidade(dto.getQuantidade());
        entity.setValorUnitario(dto.getValorUnitario());
        return entity;
    }
}
