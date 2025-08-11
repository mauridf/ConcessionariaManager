package com.concessionariamanager.application.mecanico;

import com.concessionariamanager.application.autopecas.dto.AutoPecaDTO;
import com.concessionariamanager.application.mapper.AutoPecaMapper;
import com.concessionariamanager.application.mapper.MecanicoMapper;
import com.concessionariamanager.domain.autopeca.AutoPeca;
import com.concessionariamanager.domain.mecanico.Mecanico;
import com.concessionariamanager.infrastructure.mecanico.MecanicoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class MecanicoService {
    private final MecanicoRepository mecanicoRepository;

    public MecanicoService(MecanicoRepository mecanicoRepository) {
        this.mecanicoRepository = mecanicoRepository;
    }

    public Mecanico salvar(Mecanico mecanico) {
        mecanico.setDataCadastro(LocalDateTime.now());
        mecanico.setDataAtualizacao(LocalDateTime.now());
        return mecanicoRepository.save(mecanico);
    }

    public List<Mecanico> listarTodos() {
        return mecanicoRepository.findAll();
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
