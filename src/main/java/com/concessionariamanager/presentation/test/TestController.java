package com.concessionariamanager.presentation.test;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    // Endpoint acessível somente por GERENTE
    @GetMapping("/api/test/gerente")
    @PreAuthorize("hasRole('GERENTE')")
    public String gerenteAccess() {
        return "Acesso concedido ao Gerente";
    }

    // Endpoint acessível por GERENTE e VENDEDOR
    @GetMapping("/api/test/vendedor")
    @PreAuthorize("hasAnyRole('GERENTE', 'VENDEDOR')")
    public String vendedorAccess() {
        return "Acesso concedido ao Vendedor ou Gerente";
    }

    // Endpoint acessível por todos os usuários autenticados
    @GetMapping("/api/test/all")
    @PreAuthorize("isAuthenticated()")
    public String allAccess() {
        return "Acesso concedido a todos usuários autenticados";
    }
}
