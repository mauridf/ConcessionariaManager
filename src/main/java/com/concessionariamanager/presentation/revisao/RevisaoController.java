package com.concessionariamanager.presentation.revisao;

import com.concessionariamanager.application.revisao.RevisaoService;
import com.concessionariamanager.application.revisao.dto.RevisaoDTO;
import com.concessionariamanager.application.mapper.RevisaoMapper;
import com.concessionariamanager.domain.revisao.Revisao;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/revisoes")
public class RevisaoController {

    private final RevisaoService revisaoService;

    public RevisaoController(RevisaoService revisaoService) {
        this.revisaoService = revisaoService;
    }

    // Criar revisão (Mecânico e Gerente)
    @PostMapping
    @PreAuthorize("hasAnyRole('MECANICO', 'GERENTE')")
    public ResponseEntity<RevisaoDTO> criar(@RequestBody RevisaoDTO dto) {
        Revisao revisao = RevisaoMapper.toEntity(dto, null, null); // Veiculo e mecanico serão carregados no service
        Revisao salvo = revisaoService.salvar(revisao);
        return ResponseEntity.ok(RevisaoMapper.toDTO(salvo));
    }

    // Listar todas revisões (todos autenticados)
    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<RevisaoDTO>> listarTodos() {
        List<Revisao> lista = revisaoService.listarTodos();
        List<RevisaoDTO> dtos = lista.stream()
                .map(RevisaoMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    // Buscar revisão por ID (todos autenticados)
    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<RevisaoDTO> buscarPorId(@PathVariable UUID id) {
        Revisao revisao = revisaoService.buscarPorId(id);
        return ResponseEntity.ok(RevisaoMapper.toDTO(revisao));
    }

    // Atualizar revisão (Mecânico e Gerente)
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('MECANICO', 'GERENTE')")
    public ResponseEntity<RevisaoDTO> atualizar(@PathVariable UUID id, @RequestBody RevisaoDTO dto) {
        Revisao revisaoAtualizada = RevisaoMapper.toEntity(dto, null, null);
        Revisao atualizado = revisaoService.atualizar(id, revisaoAtualizada);
        return ResponseEntity.ok(RevisaoMapper.toDTO(atualizado));
    }

    // Deletar revisão (Somente Gerente)
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('GERENTE')")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        revisaoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
