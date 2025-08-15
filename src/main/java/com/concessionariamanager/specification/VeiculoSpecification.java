package com.concessionariamanager.specification;

import com.concessionariamanager.domain.veiculo.*;
import org.springframework.data.jpa.domain.Specification;

public class VeiculoSpecification {

    public static Specification<Veiculo> marcaContains(String marca) {
        return (root, query, cb) ->
                marca == null ? null : cb.like(cb.lower(root.get("marca")), "%" + marca.toLowerCase() + "%");
    }

    public static Specification<Veiculo> modeloContains(String modelo) {
        return (root, query, cb) ->
                modelo == null ? null : cb.like(cb.lower(root.get("modelo")), "%" + modelo.toLowerCase() + "%");
    }

    public static Specification<Veiculo> tipoEquals(TipoVeiculo tipo) {
        return (root, query, cb) ->
                tipo == null ? null : cb.equal(root.get("tipoVeiculo"), tipo);
    }

    public static Specification<Veiculo> categoriaEquals(Categoria categoria) {
        return (root, query, cb) ->
                categoria == null ? null : cb.equal(root.get("categoria"), categoria);
    }

    public static Specification<Veiculo> combustivelEquals(Combustivel combustivel) {
        return (root, query, cb) ->
                combustivel == null ? null : cb.equal(root.get("combustivel"), combustivel);
    }

    public static Specification<Veiculo> cambioEquals(Cambio cambio) {
        return (root, query, cb) ->
                cambio == null ? null : cb.equal(root.get("cambio"), cambio);
    }
}

