package com.concessionariamanager.presentation.auth;

import com.concessionariamanager.application.user.UserService;
import com.concessionariamanager.application.user.dto.UserLoginDTO;
import com.concessionariamanager.application.user.dto.UserLoginResponseDTO;
import com.concessionariamanager.application.user.dto.UserRegisterDTO;
import com.concessionariamanager.application.user.dto.UserResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@Valid @RequestBody UserRegisterDTO dto) {
        UserResponseDTO user = userService.register(dto);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponseDTO> login(@Valid @RequestBody UserLoginDTO dto) {
        UserLoginResponseDTO response = userService.login(dto);
        return ResponseEntity.ok(response);
    }
}
