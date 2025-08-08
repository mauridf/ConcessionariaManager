package com.concessionariamanager.infrastructure.veiculo;

import com.concessionariamanager.domain.veiculo.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface VeiculoRepository extends JpaRepository<Veiculo, UUID>, JpaSpecificationExecutor<Veiculo> {
}
