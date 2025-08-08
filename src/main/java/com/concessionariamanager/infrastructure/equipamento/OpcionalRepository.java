package com.concessionariamanager.infrastructure.equipamento;

import com.concessionariamanager.domain.equipamento.Opcional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OpcionalRepository extends JpaRepository<Opcional, UUID> {
}
