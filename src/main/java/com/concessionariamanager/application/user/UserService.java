package com.concessionariamanager.application.user;

import com.concessionariamanager.application.user.dto.*;
import com.concessionariamanager.domain.user.Role;
import com.concessionariamanager.domain.user.User;
import com.concessionariamanager.domain.user.UserRepository;
import com.concessionariamanager.infrastructure.security.JwtService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Transactional
    public UserResponseDTO register(UserRegisterDTO dto) {
        User user = User.builder()
                .nome(dto.nome())
                .email(dto.email())
                .senhaHash(passwordEncoder.encode(dto.senha()))
                .role(dto.role())
                .build();

        userRepository.save(user);

        return new UserResponseDTO(user.getId(), user.getNome(), user.getEmail(), user.getRole());
    }

    public UserLoginResponseDTO login(UserLoginDTO dto) {
        User user = userRepository.findByEmail(dto.email())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (!passwordEncoder.matches(dto.senha(), user.getSenhaHash())) {
            throw new RuntimeException("Senha inválida");
        }

        String token = jwtService.generateToken(user.getEmail(), user.getRole().name());

        return new UserLoginResponseDTO(token, user.getNome(), user.getEmail(), user.getRole());
    }
}
