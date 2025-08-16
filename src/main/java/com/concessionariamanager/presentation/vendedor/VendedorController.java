package com.concessionariamanager.presentation.vendedor;

import com.concessionariamanager.application.vendedor.VendedorService;
import com.concessionariamanager.application.vendedor.dto.VendedorDTO;
import com.concessionariamanager.application.mapper.VendedorMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/vendedores")
public class VendedorController {

    private final VendedorService vendedorService;

    public VendedorController(VendedorService vendedorService) {
        this.vendedorService = vendedorService;
    }

    // Criar vendedor (só Gerente)
    @PostMapping
    //@PreAuthorize("hasAuthority('GERENTE')")
    public ResponseEntity<VendedorDTO> criar(@RequestBody VendedorDTO dto) {
        var vendedor = VendedorMapper.toEntity(dto);
        var salvo = vendedorService.salvar(vendedor);
        return ResponseEntity.ok(VendedorMapper.toDTO(salvo));
    }

    // Listar todos (autenticados)
    @GetMapping
    //@PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<VendedorDTO>> listarTodos() {
        var lista = vendedorService.listarTodos()
                .stream()
                .map(VendedorMapper::toDTO)
                .toList();
        return ResponseEntity.ok(lista);
    }

    // Buscar por ID (autenticados)
    @GetMapping("/{id}")
    //@PreAuthorize("isAuthenticated()")
    public ResponseEntity<VendedorDTO> buscarPorId(@PathVariable UUID id) {
        var vendedor = vendedorService.buscarPorId(id);
        return ResponseEntity.ok(VendedorMapper.toDTO(vendedor));
    }

    // Atualizar vendedor (só Gerente)
    @PutMapping("/{id}")
    //@PreAuthorize("hasAuthority('GERENTE')")
    public ResponseEntity<VendedorDTO> atualizar(@PathVariable UUID id, @RequestBody VendedorDTO dto) {
        var vendedorAtualizado = VendedorMapper.toEntity(dto);
        var atualizado = vendedorService.atualizar(id, vendedorAtualizado);
        return ResponseEntity.ok(VendedorMapper.toDTO(atualizado));
    }

    // Deletar vendedor (só Gerente)
    @DeleteMapping("/{id}")
    //@PreAuthorize("hasAuthority('GERENTE')")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        vendedorService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
