package com.concessionariamanager.application.mapper;

import com.concessionariamanager.application.veiculo.dto.VeiculoDTO;
import com.concessionariamanager.domain.veiculo.*;

public class VeiculoMapper {

    public static VeiculoDTO toDTO(Veiculo veiculo) {
        if (veiculo == null) return null;

        return new VeiculoDTO(
                veiculo.getId(),
                veiculo.getTipoVeiculo() != null ? veiculo.getTipoVeiculo().name() : null,
                veiculo.getMarca(),
                veiculo.getModelo(),
                veiculo.getVersao(),
                veiculo.getAnoFabricacao(),
                veiculo.getAnoModelo(),
                veiculo.getCor(),
                veiculo.getCombustivel() != null ? veiculo.getCombustivel().name() : null,
                veiculo.getCambio() != null ? veiculo.getCambio().name() : null,
                veiculo.getCategoria() != null ? veiculo.getCategoria().name() : null,
                veiculo.getPreco(),
                veiculo.getStatusVeiculo() != null ? veiculo.getStatusVeiculo().name() : null,
                veiculo.getDataCriacao(),
                veiculo.getDataAtualizacao()
        );
    }

    public static Veiculo toEntity(VeiculoDTO dto) {
        if (dto == null) return null;

        Veiculo veiculo = new Veiculo();
        veiculo.setId(dto.getId());

        if (dto.getTipoVeiculo() != null)
            veiculo.setTipoVeiculo(Enum.valueOf(TipoVeiculo.class, dto.getTipoVeiculo()));

        veiculo.setMarca(dto.getMarca());
        veiculo.setModelo(dto.getModelo());
        veiculo.setVersao(dto.getVersao());
        veiculo.setAnoFabricacao(dto.getAnoFabricacao());
        veiculo.setAnoModelo(dto.getAnoModelo());
        veiculo.setCor(dto.getCor());

        if (dto.getCombustivel() != null)
            veiculo.setCombustivel(Enum.valueOf(Combustivel.class, dto.getCombustivel()));

        if (dto.getCambio() != null)
            veiculo.setCambio(Enum.valueOf(Cambio.class, dto.getCambio()));

        if (dto.getCategoria() != null)
            veiculo.setCategoria(Enum.valueOf(Categoria.class, dto.getCategoria()));

        veiculo.setPreco(dto.getPreco());

        if (dto.getStatusVeiculo() != null)
            veiculo.setStatusVeiculo(Enum.valueOf(StatusVeiculo.class, dto.getStatusVeiculo()));

        return veiculo;
    }
}
