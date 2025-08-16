package com.concessionariamanager.application.user.dto;

import com.concessionariamanager.domain.user.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Email;

import java.util.UUID;

public record UserUpdateDTO(
        @NotBlank String nome,
        @NotBlank @Email String email,
        String senha,  // Opcional - pode ser null para não alterar
        @NotNull Role role
) {}
