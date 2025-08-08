package com.concessionariamanager.infrastructure.autopecas;

import com.concessionariamanager.domain.autopeca.AutoPeca;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AutoPecaRepository extends JpaRepository<AutoPeca, UUID> {
}

