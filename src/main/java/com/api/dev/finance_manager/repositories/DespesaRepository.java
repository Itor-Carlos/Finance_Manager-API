package com.api.dev.finance_manager.repositories;

import com.api.dev.finance_manager.model.Despesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DespesaRepository extends JpaRepository<Despesa,Long> {

    public List<Despesa> findByDestinoContaining(String destino);

}
