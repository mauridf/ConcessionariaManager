package com.concessionariamanager.presentation.mecanico;

import com.concessionariamanager.application.autopecas.dto.AutoPecaDTO;
import com.concessionariamanager.application.mecanico.MecanicoService;
import com.concessionariamanager.application.mecanico.dto.MecanicoDTO;
import com.concessionariamanager.application.mapper.MecanicoMapper;
import com.concessionariamanager.domain.mecanico.Mecanico;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/mecanicos")
public class MecanicoController {
    private final MecanicoService mecanicoService;

    public MecanicoController(MecanicoService mecanicoService) {
        this.mecanicoService = mecanicoService;
    }

    // Criar mecânico (só Gerente)
    @PostMapping
    @PreAuthorize("hasRole('GERENTE')")
    public ResponseEntity<MecanicoDTO> criar(@RequestBody MecanicoDTO dto) {
        var mecanico = MecanicoMapper.toEntity(dto);
        var salvo = mecanicoService.salvar(mecanico);
        return ResponseEntity.ok(MecanicoMapper.toDTO(salvo));
    }

    // Listar todos (autenticados)
    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<MecanicoDTO>> listarTodos() {
        var lista = mecanicoService.listarTodos()
                .stream()
                .map(MecanicoMapper::toDTO)
                .toList();
        return ResponseEntity.ok(lista);
    }

    // Buscar Mecânico (autenticados)
    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<MecanicoDTO> buscarPorId(@PathVariable UUID id) {
        Mecanico mecanico = mecanicoService.buscarPorId(id);
        MecanicoDTO mecanicoDTO = MecanicoMapper.toDTO(mecanico);
        return ResponseEntity.ok(mecanicoDTO);
    }

    // Atualizar mecanico (só Gerente)
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('GERENTE')")
    public ResponseEntity<MecanicoDTO> atualizar(@PathVariable UUID id, @RequestBody MecanicoDTO dto) {
        var mecanicoAtualizado = MecanicoMapper.toEntity(dto);
        var atualizado = mecanicoService.atualizar(id, mecanicoAtualizado);
        return ResponseEntity.ok(MecanicoMapper.toDTO(atualizado));
    }

    // Deletar mecanico (só Gerente)
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('GERENTE')")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        mecanicoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
