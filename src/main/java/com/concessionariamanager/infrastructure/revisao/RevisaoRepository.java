package com.concessionariamanager.infrastructure.revisao;

import com.concessionariamanager.domain.revisao.Revisao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RevisaoRepository extends JpaRepository<Revisao, UUID> {
}
