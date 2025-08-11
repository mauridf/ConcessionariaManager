package com.concessionariamanager.application.mapper;

import com.concessionariamanager.application.mecanico.dto.MecanicoDTO;
import com.concessionariamanager.domain.mecanico.Mecanico;

public class MecanicoMapper {
    public static MecanicoDTO toDTO(Mecanico mecanico) {
        if (mecanico == null) return null;

        MecanicoDTO dto = new MecanicoDTO();
        dto.setId(mecanico.getId());
        dto.setUsuarioId(mecanico.getUsuarioId());
        dto.setNome(mecanico.getNome());
        dto.setAtivo(mecanico.isAtivo());
        dto.setDataCadastro(mecanico.getDataCadastro());
        dto.setDataAtualizacao(mecanico.getDataAtualizacao());

        return dto;
    }

    public static Mecanico toEntity(MecanicoDTO dto) {
        if (dto == null) return null;

        Mecanico vendedor = new Mecanico();
        vendedor.setId(dto.getId());
        vendedor.setUsuarioId(dto.getUsuarioId());
        vendedor.setNome(dto.getNome());
        vendedor.setAtivo(dto.isAtivo());
        vendedor.setDataCadastro(dto.getDataCadastro());
        vendedor.setDataAtualizacao(dto.getDataAtualizacao());

        return vendedor;
    }
}
