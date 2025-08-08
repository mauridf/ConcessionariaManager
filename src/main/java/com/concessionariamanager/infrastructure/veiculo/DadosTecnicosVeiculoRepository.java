package com.concessionariamanager.infrastructure.veiculo;

import com.concessionariamanager.domain.veiculo.DadosTecnicosVeiculo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface DadosTecnicosVeiculoRepository extends JpaRepository<DadosTecnicosVeiculo, UUID> {

    Optional<DadosTecnicosVeiculo> findByVeiculoId(UUID veiculoId);

}

