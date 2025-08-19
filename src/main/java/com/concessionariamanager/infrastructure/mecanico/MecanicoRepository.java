package com.concessionariamanager.infrastructure.mecanico;

import com.concessionariamanager.domain.mecanico.Mecanico;
import com.concessionariamanager.domain.vendedor.Vendedor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface MecanicoRepository extends JpaRepository<Mecanico,UUID> {
    List<Mecanico> findByAtivoTrue();
    @Query("""
        select m from Mecanico m 
        join User u on m.usuarioId = u.id 
        where u.role = 'MECANICO'
    """)
    Page<Mecanico> findAllMecanicos(Pageable pageable);
}
