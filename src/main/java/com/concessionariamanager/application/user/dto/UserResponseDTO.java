package com.concessionariamanager.application.user.dto;

import com.concessionariamanager.domain.user.Role;

import java.util.UUID;

public record UserResponseDTO(
        UUID id,
        String nome,
        String email,
        Role role
) {}
