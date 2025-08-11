package com.concessionariamanager.infrastructure.mecanico;

import com.concessionariamanager.domain.mecanico.Mecanico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MecanicoRepository extends JpaRepository<Mecanico,UUID> {
    List<Mecanico> findByAtivoTrue();
}
