package com.api.dev.finance_manager.repositories.repository;

import com.api.dev.finance_manager.enums.DespesaStatus;
import com.api.dev.finance_manager.model.Despesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface DespesaRepository extends JpaRepository<Despesa,Long> {

    public List<Despesa> find(Long id, String destino, Date data, DespesaStatus despesaStatus);

}
