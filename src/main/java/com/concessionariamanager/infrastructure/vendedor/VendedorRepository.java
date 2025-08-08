package com.concessionariamanager.infrastructure.vendedor;

import com.concessionariamanager.domain.vendedor.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface VendedorRepository extends JpaRepository<Vendedor, UUID> {
    List<Vendedor> findByAtivoTrue();
}
