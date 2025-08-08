package com.concessionariamanager.application.vendedor;

import com.concessionariamanager.domain.vendedor.Vendedor;
import com.concessionariamanager.infrastructure.vendedor.VendedorRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class VendedorService {

    private final VendedorRepository vendedorRepository;

    public VendedorService(VendedorRepository vendedorRepository) {
        this.vendedorRepository = vendedorRepository;
    }

    public Vendedor salvar(Vendedor vendedor) {
        vendedor.setDataCadastro(LocalDateTime.now());
        vendedor.setDataAtualizacao(LocalDateTime.now());
        return vendedorRepository.save(vendedor);
    }

    public List<Vendedor> listarTodos() {
        return vendedorRepository.findAll();
    }

    public List<Vendedor> listarAtivos() {
        return vendedorRepository.findByAtivoTrue();
    }

    public Vendedor buscarPorId(UUID id) {
        return vendedorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vendedor não encontrado"));
    }

    public Vendedor atualizar(UUID id, Vendedor vendedorAtualizado) {
        Vendedor existente = buscarPorId(id);
        existente.setNome(vendedorAtualizado.getNome());
        existente.setAtivo(vendedorAtualizado.isAtivo());
        existente.setUsuarioId(vendedorAtualizado.getUsuarioId());
        existente.setDataAtualizacao(LocalDateTime.now());
        return vendedorRepository.save(existente);
    }

    public void deletar(UUID id) {
        Vendedor existente = buscarPorId(id);
        vendedorRepository.delete(existente);
    }
}
