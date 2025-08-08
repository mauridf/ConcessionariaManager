package com.concessionariamanager.application.user.dto;

import com.concessionariamanager.domain.user.Role;

public record UserLoginResponseDTO(
        String token,
        String nome,
        String email,
        Role role
) {}
