package com.api.dev.finance_manager.repositories;

import com.api.dev.finance_manager.model.Despesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DespesaRepository extends JpaRepository<Despesa,Long> {
}
