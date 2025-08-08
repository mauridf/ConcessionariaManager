package com.concessionariamanager.application.cliente;

import com.concessionariamanager.domain.cliente.Cliente;
import com.concessionariamanager.infrastructure.cliente.ClienteRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente criarCliente(Cliente cliente) {
        cliente.setDataCadastro(LocalDate.now());
        return clienteRepository.save(cliente);
    }

    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> buscarPorId(Long id) {
        return clienteRepository.findById(id);
    }

    public Cliente atualizarCliente(Long id, Cliente clienteAtualizado) {
        return clienteRepository.findById(id)
                .map(cliente -> {
                    cliente.setNome(clienteAtualizado.getNome());
                    cliente.setCpf(clienteAtualizado.getCpf());
                    cliente.setEmail(clienteAtualizado.getEmail());
                    cliente.setTelefone(clienteAtualizado.getTelefone());
                    cliente.setEndereco(clienteAtualizado.getEndereco());
                    cliente.setDataNascimento(clienteAtualizado.getDataNascimento());
                    return clienteRepository.save(cliente);
                }).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    }

    public void excluirCliente(Long id) {
        clienteRepository.deleteById(id);
    }
}
