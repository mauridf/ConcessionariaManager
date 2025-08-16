package com.concessionariamanager.presentation.veiculo;

import com.concessionariamanager.application.veiculo.DocumentacaoVeiculoService;
import com.concessionariamanager.application.veiculo.dto.DocumentacaoVeiculoDTO;
import com.concessionariamanager.application.mapper.DocumentacaoVeiculoMapper;
import com.concessionariamanager.domain.veiculo.DocumentacaoVeiculo;
import com.concessionariamanager.domain.veiculo.Veiculo;
import com.concessionariamanager.infrastructure.veiculo.VeiculoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/documentacao")
public class DocumentacaoVeiculoController {

    private final DocumentacaoVeiculoService service;
    private final VeiculoRepository veiculoRepository;

    public DocumentacaoVeiculoController(DocumentacaoVeiculoService service, VeiculoRepository veiculoRepository) {
        this.service = service;
        this.veiculoRepository = veiculoRepository;
    }

    @PostMapping
    //@PreAuthorize("hasAnyRole('GERENTE', 'VENDEDOR')")
    public ResponseEntity<DocumentacaoVeiculoDTO> criar(@RequestBody DocumentacaoVeiculoDTO dto) {
        Veiculo veiculo = veiculoRepository.findById(dto.getVeiculoId())
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado"));
        DocumentacaoVeiculo entity = DocumentacaoVeiculoMapper.toEntity(dto, veiculo);
        DocumentacaoVeiculo salvo = service.salvar(entity);
        return ResponseEntity.ok(DocumentacaoVeiculoMapper.toDTO(salvo));
    }

    @GetMapping("/{id}")
    //@PreAuthorize("isAuthenticated()")
    public ResponseEntity<DocumentacaoVeiculoDTO> buscarPorId(@PathVariable UUID id) {
        DocumentacaoVeiculo entity = service.buscarPorId(id);
        return ResponseEntity.ok(DocumentacaoVeiculoMapper.toDTO(entity));
    }

    @GetMapping("/veiculo/{veiculoId}")
    //@PreAuthorize("isAuthenticated()")
    public ResponseEntity<DocumentacaoVeiculoDTO> buscarPorVeiculoId(@PathVariable UUID veiculoId) {
        DocumentacaoVeiculo entity = service.buscarPorVeiculoId(veiculoId);
        return ResponseEntity.ok(DocumentacaoVeiculoMapper.toDTO(entity));
    }

    @PutMapping("/{id}")
    //@PreAuthorize("hasAnyRole('GERENTE', 'VENDEDOR')")
    public ResponseEntity<DocumentacaoVeiculoDTO> atualizar(@PathVariable UUID id,
                                                            @RequestBody DocumentacaoVeiculoDTO dto) {
        Veiculo veiculo = veiculoRepository.findById(dto.getVeiculoId())
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado"));
        DocumentacaoVeiculo entity = DocumentacaoVeiculoMapper.toEntity(dto, veiculo);
        DocumentacaoVeiculo atualizado = service.atualizar(id, entity);
        return ResponseEntity.ok(DocumentacaoVeiculoMapper.toDTO(atualizado));
    }

    @DeleteMapping("/{id}")
    //@PreAuthorize("hasRole('GERENTE')")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
