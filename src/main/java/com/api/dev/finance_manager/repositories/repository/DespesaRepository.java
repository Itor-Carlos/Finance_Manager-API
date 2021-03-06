package com.api.dev.finance_manager.repositories.repository;

import com.api.dev.finance_manager.enums.DespesaCategoria;
import com.api.dev.finance_manager.enums.DespesaStatus;
import com.api.dev.finance_manager.model.Despesa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Repository
public interface DespesaRepository extends JpaRepository<Despesa,Long> {

    public Page<Despesa> find(Long id, String destino, Date data, DespesaStatus despesaStatus, DespesaCategoria despesaCategoria, Pageable pageable);

    @Transactional
    public void updateDespesa(Long id, Despesa despesa);

    @Transactional
    public void deleteById(Long id);
}
