package com.concessionariamanager.application.mapper;

import com.concessionariamanager.application.revisao.dto.ItemRevisaoDTO;
import com.concessionariamanager.application.revisao.dto.RevisaoDTO;
import com.concessionariamanager.domain.revisao.ItemRevisao;
import com.concessionariamanager.domain.revisao.Revisao;
import com.concessionariamanager.domain.user.User;
import com.concessionariamanager.domain.veiculo.Veiculo;

import java.util.List;
import java.util.stream.Collectors;

public class RevisaoMapper {

    public static RevisaoDTO toDTO(Revisao entity) {
        if (entity == null) return null;

        RevisaoDTO dto = new RevisaoDTO();
        dto.setId(entity.getId());
        dto.setVeiculoId(entity.getVeiculo() != null ? entity.getVeiculo().getId() : null);
        dto.setMecanicoId(entity.getMecanico() != null ? entity.getMecanico().getId() : null);
        dto.setDataRevisao(entity.getDataRevisao());
        dto.setQuilometragemAtual(entity.getQuilometragemAtual());
        dto.setDescricao(entity.getDescricao());
        dto.setValorTotal(entity.getValorTotal());

        List<ItemRevisaoDTO> itensDTO = entity.getItens() == null ? List.of() :
                entity.getItens().stream()
                        .map(ItemRevisaoMapper::toDTO)
                        .collect(Collectors.toList());
        dto.setItens(itensDTO);

        return dto;
    }

    public static Revisao toEntity(RevisaoDTO dto, Veiculo veiculo, User mecanico) {
        if (dto == null) return null;

        Revisao entity = new Revisao();
        entity.setId(dto.getId());
        entity.setVeiculo(veiculo);
        entity.setMecanico(mecanico);
        entity.setDataRevisao(dto.getDataRevisao());
        entity.setQuilometragemAtual(dto.getQuilometragemAtual());
        entity.setDescricao(dto.getDescricao());
        entity.setValorTotal(dto.getValorTotal());

        List<ItemRevisao> itens = dto.getItens() == null ? List.of() :
                dto.getItens().stream()
                        .map(i -> ItemRevisaoMapper.toEntity(i, entity))
                        .collect(Collectors.toList());
        entity.setItens(itens);

        return entity;
    }
}
