package com.concessionariamanager.application.mapper;

import com.concessionariamanager.application.vendedor.dto.VendedorDTO;
import com.concessionariamanager.domain.vendedor.Vendedor;

public class VendedorMapper {

    public static VendedorDTO toDTO(Vendedor vendedor) {
        if (vendedor == null) return null;

        VendedorDTO dto = new VendedorDTO();
        dto.setId(vendedor.getId());
        dto.setUsuarioId(vendedor.getUsuarioId());
        dto.setNome(vendedor.getNome());
        dto.setAtivo(vendedor.isAtivo());
        dto.setDataCadastro(vendedor.getDataCadastro());
        dto.setDataAtualizacao(vendedor.getDataAtualizacao());

        return dto;
    }

    public static Vendedor toEntity(VendedorDTO dto) {
        if (dto == null) return null;

        Vendedor vendedor = new Vendedor();
        vendedor.setId(dto.getId());
        vendedor.setUsuarioId(dto.getUsuarioId());
        vendedor.setNome(dto.getNome());
        vendedor.setAtivo(dto.isAtivo());
        vendedor.setDataCadastro(dto.getDataCadastro());
        vendedor.setDataAtualizacao(dto.getDataAtualizacao());

        return vendedor;
    }
}
