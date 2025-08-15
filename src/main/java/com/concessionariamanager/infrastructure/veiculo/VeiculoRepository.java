package com.concessionariamanager.infrastructure.veiculo;

import com.concessionariamanager.domain.veiculo.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface VeiculoRepository extends JpaRepository<Veiculo, UUID>, JpaSpecificationExecutor<Veiculo> {
    @Query("SELECT DISTINCT v.marca FROM Veiculo v ORDER BY v.marca ASC")
    List<String> findDistinctMarcas();

    @Query("SELECT DISTINCT v.modelo FROM Veiculo v ORDER BY v.modelo ASC")
    List<String> findDistinctModelos();
}
