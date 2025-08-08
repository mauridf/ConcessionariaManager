package com.concessionariamanager.presentation.venda;

import com.concessionariamanager.application.venda.VendaService;
import com.concessionariamanager.application.venda.dto.VendaDTO;
import com.concessionariamanager.application.mapper.VendaMapper;
import com.concessionariamanager.domain.venda.Venda;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/vendas")
public class VendaController {

    private final VendaService vendaService;

    public VendaController(VendaService vendaService) {
        this.vendaService = vendaService;
    }

    // Criar venda (Gerente ou Vendedor)
    @PostMapping
    @PreAuthorize("hasAnyRole('GERENTE', 'VENDEDOR')")
    public ResponseEntity<VendaDTO> criar(@RequestBody VendaDTO dto) {
        Venda venda = VendaMapper.toEntity(dto, null, null, null, null);
        Venda salvo = vendaService.salvar(venda);
        return ResponseEntity.ok(VendaMapper.toDTO(salvo));
    }

    // Listar todas vendas (todos autenticados)
    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<VendaDTO>> listarTodos() {
        List<Venda> lista = vendaService.listarTodos();
        List<VendaDTO> dtos = lista.stream()
                .map(VendaMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    // Buscar venda por ID (todos autenticados)
    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<VendaDTO> buscarPorId(@PathVariable UUID id) {
        Venda venda = vendaService.buscarPorId(id);
        return ResponseEntity.ok(VendaMapper.toDTO(venda));
    }

    // Atualizar venda (Gerente ou Vendedor)
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('GERENTE', 'VENDEDOR')")
    public ResponseEntity<VendaDTO> atualizar(@PathVariable UUID id, @RequestBody VendaDTO dto) {
        Venda vendaAtualizada = VendaMapper.toEntity(dto, null, null, null, null);
        Venda atualizado = vendaService.atualizar(id, vendaAtualizada);
        return ResponseEntity.ok(VendaMapper.toDTO(atualizado));
    }

    // Deletar venda (Somente Gerente)
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('GERENTE')")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        vendaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
