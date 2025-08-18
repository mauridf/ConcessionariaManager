package com.concessionariamanager.presentation.cliente;

import com.concessionariamanager.application.cliente.ClienteService;
import com.concessionariamanager.domain.cliente.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    //@PreAuthorize("hasAuthority('GERENTE') or hasAuthority('VENDEDOR')")
    public Cliente criarCliente(@RequestBody Cliente cliente) {
        return clienteService.criarCliente(cliente);
    }

    @GetMapping
    //@PreAuthorize("hasAnyAuthority('GERENTE', 'VENDEDOR', 'MECANICO')")
    public Page<Cliente> listarClientes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String nome) {
        return clienteService.listarClientes(PageRequest.of(page, size), nome);
    }

    @GetMapping("/{id}")
    //@PreAuthorize("hasAnyAuthority('GERENTE', 'VENDEDOR', 'MECANICO')")
    public Cliente buscarPorId(@PathVariable Long id) {
        return clienteService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    }

    @PutMapping("/{id}")
    //@PreAuthorize("hasAuthority('GERENTE') or hasAuthority('VENDEDOR')")
    public Cliente atualizarCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        return clienteService.atualizarCliente(id, cliente);
    }

    @DeleteMapping("/{id}")
    //@PreAuthorize("hasAuthority('GERENTE')")
    public void excluirCliente(@PathVariable Long id) {
        clienteService.excluirCliente(id);
    }
}
