package com.concessionariamanager.presentation.equipamento;

import com.concessionariamanager.application.equipamento.EquipamentoOpcionalService;
import com.concessionariamanager.application.equipamento.dto.EquipamentoOpcionalDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/equipamentos-opcionais")
public class EquipamentoOpcionalController {

    private final EquipamentoOpcionalService equipamentoOpcionalService;

    public EquipamentoOpcionalController(EquipamentoOpcionalService equipamentoOpcionalService) {
        this.equipamentoOpcionalService = equipamentoOpcionalService;
    }

    @PostMapping
    //@PreAuthorize("hasAnyAuthority('GERENTE', 'VENDEDOR')")
    public ResponseEntity<EquipamentoOpcionalDTO> criar(@RequestBody EquipamentoOpcionalDTO dto) {
        EquipamentoOpcionalDTO criado = equipamentoOpcionalService.salvar(dto);
        return ResponseEntity.ok(criado);
    }

    @GetMapping
    //@PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<EquipamentoOpcionalDTO>> listarTodos() {
        return ResponseEntity.ok(equipamentoOpcionalService.listarTodos());
    }

    @GetMapping("/{id}")
    //@PreAuthorize("isAuthenticated()")
    public ResponseEntity<EquipamentoOpcionalDTO> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(equipamentoOpcionalService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    //@PreAuthorize("hasAnyAuthority('GERENTE', 'VENDEDOR')")
    public ResponseEntity<EquipamentoOpcionalDTO> atualizar(@PathVariable UUID id, @RequestBody EquipamentoOpcionalDTO dto) {
        return ResponseEntity.ok(equipamentoOpcionalService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    //@PreAuthorize("hasAuthority('GERENTE')")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        equipamentoOpcionalService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
