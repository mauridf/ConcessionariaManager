package com.concessionariamanager.application.user.dto;

import com.concessionariamanager.domain.user.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserRegisterDTO(
        @NotBlank String nome,
        @Email String email,
        @NotBlank String senha,
        @NotNull Role role
) {}
