package com.concessionariamanager.presentation.user;

import com.concessionariamanager.application.user.UserService;
import com.concessionariamanager.application.user.dto.UserRegisterDTO;
import com.concessionariamanager.application.user.dto.UserResponseDTO;
import com.concessionariamanager.domain.user.Role;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // Buscar usuário por ID (autenticados)
    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<UserResponseDTO> getById(@PathVariable UUID id) {
        UserResponseDTO user = userService.findById(id);
        return ResponseEntity.ok(user);
    }

    // Buscar usuários por role (paginado)
    @GetMapping("/role/{role}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Page<UserResponseDTO>> getByRole(
            @PathVariable Role role,
            Pageable pageable) {
        Page<UserResponseDTO> users = userService.findByRole(role, pageable);
        return ResponseEntity.ok(users);
    }

    // Listar todos os usuários (paginado)
    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Page<UserResponseDTO>> getAll(Pageable pageable) {
        Page<UserResponseDTO> users = userService.findAll(pageable);
        return ResponseEntity.ok(users);
    }

    // Atualizar usuário (somente GERENTE)
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('GERENTE')")
    public ResponseEntity<UserResponseDTO> update(@PathVariable UUID id,
                                                  @Valid @RequestBody UserRegisterDTO dto) {
        UserResponseDTO updated = userService.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    // Deletar usuário (somente GERENTE)
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('GERENTE')")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
