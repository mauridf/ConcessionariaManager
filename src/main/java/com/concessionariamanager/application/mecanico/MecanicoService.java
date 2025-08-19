package com.concessionariamanager.application.mecanico;

import com.concessionariamanager.application.autopecas.dto.AutoPecaDTO;
import com.concessionariamanager.application.mapper.AutoPecaMapper;
import com.concessionariamanager.application.mapper.MecanicoMapper;
import com.concessionariamanager.application.mapper.VendedorMapper;
import com.concessionariamanager.application.mecanico.dto.MecanicoDTO;
import com.concessionariamanager.application.user.UserService;
import com.concessionariamanager.application.vendedor.dto.VendedorDTO;
import com.concessionariamanager.domain.autopeca.AutoPeca;
import com.concessionariamanager.domain.mecanico.Mecanico;
import com.concessionariamanager.infrastructure.mecanico.MecanicoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class MecanicoService {
    private final MecanicoRepository mecanicoRepository;
    private final UserService userService;

    public MecanicoService(MecanicoRepository mecanicoRepository, UserService userService) {
        this.mecanicoRepository = mecanicoRepository;
        this.userService = userService;
    }

    public Mecanico salvar(Mecanico mecanico) {
        mecanico.setDataCadastro(LocalDateTime.now());
        mecanico.setDataAtualizacao(LocalDateTime.now());
        return mecanicoRepository.save(mecanico);
    }

    public Page<MecanicoDTO> listarTodos(Pageable pageable) {
        return mecanicoRepository.findAllMecanicos(pageable)
                .map(mecanico -> {
                    MecanicoDTO dto = MecanicoMapper.toDTO(mecanico);
                    var user = userService.findById(mecanico.getUsuarioId());
                    dto.setUsuarioNome(user.nome());
                    dto.setUsuarioEmail(user.email());
                    dto.setUsuarioRole(user.role().name());
                    return dto;
                });
    }

    public List<Mecanico> listarAtivos() {
        return mecanicoRepository.findByAtivoTrue();
    }

    public Mecanico buscarPorId(UUID id) {
        return mecanicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mecânico não encontrado"));
    }

    public Mecanico atualizar(UUID id, Mecanico mecanicoAtualizado) {
        Mecanico existente = buscarPorId(id);
        existente.setNome(mecanicoAtualizado.getNome());
        existente.setAtivo(mecanicoAtualizado.isAtivo());
        existente.setUsuarioId(mecanicoAtualizado.getUsuarioId());
        existente.setDataAtualizacao(LocalDateTime.now());
        return mecanicoRepository.save(existente);
    }

    public void deletar(UUID id) {
        Mecanico existente = buscarPorId(id);
        mecanicoRepository.delete(existente);
    }
}
