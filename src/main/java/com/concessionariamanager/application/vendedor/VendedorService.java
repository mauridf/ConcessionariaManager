package com.concessionariamanager.application.vendedor;

import com.concessionariamanager.application.mapper.VendedorMapper;
import com.concessionariamanager.application.user.UserService;
import com.concessionariamanager.application.vendedor.dto.VendedorDTO;
import com.concessionariamanager.domain.vendedor.Vendedor;
import com.concessionariamanager.infrastructure.vendedor.VendedorRepository;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class VendedorService {

    private final VendedorRepository vendedorRepository;
    private final UserService userService;

    public VendedorService(VendedorRepository vendedorRepository, UserService userService) {
        this.vendedorRepository = vendedorRepository;
        this.userService = userService;
    }

    public Vendedor salvar(Vendedor vendedor) {
        vendedor.setDataCadastro(LocalDateTime.now());
        vendedor.setDataAtualizacao(LocalDateTime.now());
        return vendedorRepository.save(vendedor);
    }

    public Page<VendedorDTO> listarTodos(Pageable pageable) {
        return vendedorRepository.findAllVendedores(pageable)
                .map(vendedor -> {
                    VendedorDTO dto = VendedorMapper.toDTO(vendedor);
                    var user = userService.findById(vendedor.getUsuarioId());
                    dto.setUsuarioNome(user.nome());
                    dto.setUsuarioEmail(user.email());
                    dto.setUsuarioRole(user.role().name());
                    return dto;
                });
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
