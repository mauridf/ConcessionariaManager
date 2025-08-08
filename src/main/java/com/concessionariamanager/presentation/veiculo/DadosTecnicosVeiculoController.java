package com.concessionariamanager.presentation.veiculo;

import com.concessionariamanager.application.veiculo.DadosTecnicosVeiculoService;
import com.concessionariamanager.application.veiculo.dto.DadosTecnicosVeiculoDTO;
import com.concessionariamanager.application.mapper.DadosTecnicosVeiculoMapper;
import com.concessionariamanager.domain.veiculo.DadosTecnicosVeiculo;
import com.concessionariamanager.domain.veiculo.Veiculo;
import com.concessionariamanager.infrastructure.veiculo.VeiculoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/dados-tecnicos")
public class DadosTecnicosVeiculoController {

    private final DadosTecnicosVeiculoService service;
    private final VeiculoRepository veiculoRepository;

    public DadosTecnicosVeiculoController(DadosTecnicosVeiculoService service, VeiculoRepository veiculoRepository) {
        this.service = service;
        this.veiculoRepository = veiculoRepository;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('GERENTE', 'VENDEDOR')")
    public ResponseEntity<DadosTecnicosVeiculoDTO> criar(@RequestBody DadosTecnicosVeiculoDTO dto) {
        Veiculo veiculo = veiculoRepository.findById(dto.getVeiculoId())
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado"));
        DadosTecnicosVeiculo entity = DadosTecnicosVeiculoMapper.toEntity(dto, veiculo);
        DadosTecnicosVeiculo salvo = service.salvar(entity);
        return ResponseEntity.ok(DadosTecnicosVeiculoMapper.toDTO(salvo));
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<DadosTecnicosVeiculoDTO> buscarPorId(@PathVariable UUID id) {
        DadosTecnicosVeiculo entity = service.buscarPorId(id);
        return ResponseEntity.ok(DadosTecnicosVeiculoMapper.toDTO(entity));
    }

    @GetMapping("/veiculo/{veiculoId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<DadosTecnicosVeiculoDTO> buscarPorVeiculoId(@PathVariable UUID veiculoId) {
        DadosTecnicosVeiculo entity = service.buscarPorVeiculoId(veiculoId);
        return ResponseEntity.ok(DadosTecnicosVeiculoMapper.toDTO(entity));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('GERENTE', 'VENDEDOR')")
    public ResponseEntity<DadosTecnicosVeiculoDTO> atualizar(@PathVariable UUID id,
                                                             @RequestBody DadosTecnicosVeiculoDTO dto) {
        Veiculo veiculo = veiculoRepository.findById(dto.getVeiculoId())
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado"));
        DadosTecnicosVeiculo entity = DadosTecnicosVeiculoMapper.toEntity(dto, veiculo);
        DadosTecnicosVeiculo atualizado = service.atualizar(id, entity);
        return ResponseEntity.ok(DadosTecnicosVeiculoMapper.toDTO(atualizado));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('GERENTE')")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
