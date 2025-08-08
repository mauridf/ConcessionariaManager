package com.concessionariamanager.application.veiculo;

import com.concessionariamanager.domain.veiculo.DocumentacaoVeiculo;
import com.concessionariamanager.domain.veiculo.Veiculo;
import com.concessionariamanager.infrastructure.veiculo.DocumentacaoVeiculoRepository;
import com.concessionariamanager.infrastructure.veiculo.VeiculoRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DocumentacaoVeiculoService {

    private final DocumentacaoVeiculoRepository documentacaoVeiculoRepository;
    private final VeiculoRepository veiculoRepository;

    public DocumentacaoVeiculoService(DocumentacaoVeiculoRepository documentacaoVeiculoRepository,
                                      VeiculoRepository veiculoRepository) {
        this.documentacaoVeiculoRepository = documentacaoVeiculoRepository;
        this.veiculoRepository = veiculoRepository;
    }

    public DocumentacaoVeiculo salvar(DocumentacaoVeiculo documentacao) {
        Veiculo veiculo = veiculoRepository.findById(documentacao.getVeiculo().getId())
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado para documentação"));
        documentacao.setVeiculo(veiculo);
        return documentacaoVeiculoRepository.save(documentacao);
    }

    public DocumentacaoVeiculo buscarPorId(UUID id) {
        return documentacaoVeiculoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Documentação do veículo não encontrada"));
    }

    public DocumentacaoVeiculo buscarPorVeiculoId(UUID veiculoId) {
        return documentacaoVeiculoRepository.findByVeiculoId(veiculoId)
                .orElseThrow(() -> new RuntimeException("Documentação para o veículo não encontrada"));
    }

    public DocumentacaoVeiculo atualizar(UUID id, DocumentacaoVeiculo documentacaoAtualizada) {
        DocumentacaoVeiculo existente = buscarPorId(id);
        existente.setPossuiIPVA(documentacaoAtualizada.isPossuiIPVA());
        existente.setLicenciamentoOK(documentacaoAtualizada.isLicenciamentoOK());
        existente.setMultasPendentes(documentacaoAtualizada.isMultasPendentes());
        existente.setValorMultas(documentacaoAtualizada.getValorMultas());
        existente.setOrigem(documentacaoAtualizada.getOrigem());

        return documentacaoVeiculoRepository.save(existente);
    }

    public void deletar(UUID id) {
        DocumentacaoVeiculo existente = buscarPorId(id);
        documentacaoVeiculoRepository.delete(existente);
    }
}
