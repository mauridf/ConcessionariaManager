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

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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

    public UserResponseDTO findById(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        return toDTO(user);
    }

    public Page<UserResponseDTO> findByRole(Role role, Pageable pageable) {
        return userRepository.findByRole(role, pageable)
                .map(this::toDTO);
    }

    public Page<UserResponseDTO> findAll(Pageable pageable) {
        return userRepository.findAll(pageable)
                .map(this::toDTO);
    }

    @Transactional
    public UserResponseDTO update(UUID id, UserRegisterDTO dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        user.setNome(dto.nome());
        user.setEmail(dto.email());
        if (dto.senha() != null && !dto.senha().isBlank()) {
            user.setSenhaHash(passwordEncoder.encode(dto.senha()));
        }
        user.setRole(dto.role());

        userRepository.save(user);

        return toDTO(user);
    }

    public void delete(UUID id) {
        userRepository.deleteById(id);
    }

    private UserResponseDTO toDTO(User user) {
        return new UserResponseDTO(user.getId(), user.getNome(), user.getEmail(), user.getRole());
    }
}
