package com.concessionariamanager.infrastructure.equipamento;

import com.concessionariamanager.domain.equipamento.EquipamentoOpcional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EquipamentoOpcionalRepository extends JpaRepository<EquipamentoOpcional, UUID> {
}


