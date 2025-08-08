package com.concessionariamanager.application.veiculo.dto;

import com.concessionariamanager.domain.veiculo.Categoria;
import com.concessionariamanager.domain.veiculo.TipoVeiculo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VeiculoFiltroDTO {
    private String marca;
    private String modelo;
    private TipoVeiculo tipoVeiculo;
    private Categoria categoria;
}

