package com.concessionariamanager.infrastructure.veiculo;

import com.concessionariamanager.domain.veiculo.DocumentacaoVeiculo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface DocumentacaoVeiculoRepository extends JpaRepository<DocumentacaoVeiculo, UUID> {

    Optional<DocumentacaoVeiculo> findByVeiculoId(UUID veiculoId);

}
