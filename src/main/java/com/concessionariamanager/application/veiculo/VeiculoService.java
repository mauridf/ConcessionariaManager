package com.concessionariamanager.application.veiculo;

import com.concessionariamanager.application.veiculo.dto.VeiculoDTO;
import com.concessionariamanager.application.veiculo.dto.VeiculoFiltroDTO;
import com.concessionariamanager.domain.veiculo.Veiculo;
import com.concessionariamanager.infrastructure.veiculo.VeiculoRepository;
import org.springframework.stereotype.Service;
import com.concessionariamanager.application.mapper.VeiculoMapper;

import com.concessionariamanager.specification.VeiculoSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.UUID;

@Service
public class VeiculoService {

    private final VeiculoRepository veiculoRepository;

    public VeiculoService(VeiculoRepository veiculoRepository) {
        this.veiculoRepository = veiculoRepository;
    }

    public Veiculo salvar(Veiculo veiculo) {
        return veiculoRepository.save(veiculo);
    }

    /*public List<Veiculo> listarTodos() {
        return veiculoRepository.findAll();
    }*/

    public Page<VeiculoDTO> listar(VeiculoFiltroDTO filtro, Pageable pageable) {
        Specification<Veiculo> spec = Specification.allOf(
                VeiculoSpecification.marcaContains(filtro.getMarca()),
                VeiculoSpecification.modeloContains(filtro.getModelo()),
                VeiculoSpecification.tipoEquals(filtro.getTipoVeiculo()),
                VeiculoSpecification.categoriaEquals(filtro.getCategoria())
        );

        return veiculoRepository.findAll(spec, pageable)
                .map(VeiculoMapper::toDTO);
    }

    public Veiculo buscarPorId(UUID id) {
        return veiculoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado"));
    }

    public Veiculo atualizar(UUID id, Veiculo veiculoAtualizado) {
        Veiculo veiculoExistente = buscarPorId(id);

        veiculoExistente.setTipoVeiculo(veiculoAtualizado.getTipoVeiculo());
        veiculoExistente.setMarca(veiculoAtualizado.getMarca());
        veiculoExistente.setModelo(veiculoAtualizado.getModelo());
        veiculoExistente.setVersao(veiculoAtualizado.getVersao());
        veiculoExistente.setAnoFabricacao(veiculoAtualizado.getAnoFabricacao());
        veiculoExistente.setAnoModelo(veiculoAtualizado.getAnoModelo());
        veiculoExistente.setCor(veiculoAtualizado.getCor());
        veiculoExistente.setCombustivel(veiculoAtualizado.getCombustivel());
        veiculoExistente.setCambio(veiculoAtualizado.getCambio());
        veiculoExistente.setCategoria(veiculoAtualizado.getCategoria());

        return veiculoRepository.save(veiculoExistente);
    }

    public void deletar(UUID id) {
        Veiculo veiculo = buscarPorId(id);
        veiculoRepository.delete(veiculo);
    }
}
