package com.concessionariamanager.infrastructure.vendedor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.concessionariamanager.domain.vendedor.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface VendedorRepository extends JpaRepository<Vendedor, UUID> {
    List<Vendedor> findByAtivoTrue();
    @Query("""
        SELECT v FROM Vendedor v
        JOIN User u ON v.usuarioId = u.id
        WHERE u.role = 'VENDEDOR'
    """)
    Page<Vendedor> findAllVendedores(Pageable pageable);
}
