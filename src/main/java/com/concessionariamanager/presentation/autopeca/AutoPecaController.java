package com.concessionariamanager.presentation.autopeca;

import com.concessionariamanager.application.autopecas.AutoPecaService;
import com.concessionariamanager.application.autopecas.dto.AutoPecaDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/autopecas")
public class AutoPecaController {

    private final AutoPecaService autoPecaService;

    public AutoPecaController(AutoPecaService autoPecaService) {
        this.autoPecaService = autoPecaService;
    }

    @PostMapping
    //@PreAuthorize("hasAnyAuthority('GERENTE', 'MECANICO')")
    public ResponseEntity<AutoPecaDTO> criar(@RequestBody AutoPecaDTO dto) {
        AutoPecaDTO criado = autoPecaService.salvar(dto);
        return ResponseEntity.ok(criado);
    }

    @GetMapping
    //@PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<AutoPecaDTO>> listarTodos() {
        return ResponseEntity.ok(autoPecaService.listarTodos());
    }

    @GetMapping("/{id}")
    //@PreAuthorize("isAuthenticated()")
    public ResponseEntity<AutoPecaDTO> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(autoPecaService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    //@PreAuthorize("hasAnyAuthority('GERENTE', 'MECANICO')")
    public ResponseEntity<AutoPecaDTO> atualizar(@PathVariable UUID id, @RequestBody AutoPecaDTO dto) {
        return ResponseEntity.ok(autoPecaService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    //@PreAuthorize("hasAuthority('GERENTE')")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        autoPecaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
