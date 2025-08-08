package com.concessionariamanager.application.mapper;

import com.concessionariamanager.application.venda.dto.VendaDTO;
import com.concessionariamanager.domain.venda.Venda;
import com.concessionariamanager.domain.cliente.Cliente;
import com.concessionariamanager.domain.veiculo.Veiculo;
import com.concessionariamanager.domain.user.User;
import com.concessionariamanager.domain.equipamento.EquipamentoOpcional;

public class VendaMapper {

    public static VendaDTO toDTO(Venda entity) {
        if (entity == null) return null;

        VendaDTO dto = new VendaDTO();
        dto.setId(entity.getId()); // UUID
        dto.setClienteId(entity.getCliente() != null ? entity.getCliente().getId() : null);
        dto.setVeiculoId(entity.getVeiculo() != null ? entity.getVeiculo().getId() : null);
        dto.setVendedorId(entity.getVendedor() != null ? entity.getVendedor().getId() : null);
        dto.setEquipamentosOpcionaisId(entity.getEquipamentosOpcionais() != null ? entity.getEquipamentosOpcionais().getId() : null);
        dto.setValorCompra(entity.getValorCompra());
        dto.setValorDescontos(entity.getValorDescontos());
        dto.setValorFinal(entity.getValorFinal());
        dto.setDataVenda(entity.getDataVenda());

        return dto;
    }

    public static Venda toEntity(VendaDTO dto, Cliente cliente, Veiculo veiculo, User vendedor, EquipamentoOpcional equipamentosOpcionais) {
        if (dto == null) return null;

        Venda entity = new Venda();
        entity.setId(dto.getId()); // UUID
        entity.setCliente(cliente);
        entity.setVeiculo(veiculo);
        entity.setVendedor(vendedor);
        entity.setEquipamentosOpcionais(equipamentosOpcionais);
        entity.setValorCompra(dto.getValorCompra());
        entity.setValorDescontos(dto.getValorDescontos());
        entity.setValorFinal(dto.getValorFinal());
        entity.setDataVenda(dto.getDataVenda());

        return entity;
    }
}
