package com.api.dev.finance_manager.services;

import com.api.dev.finance_manager.enums.DespesaCategoria;
import com.api.dev.finance_manager.enums.DespesaStatus;
import com.api.dev.finance_manager.exceptions.DespesaNotFoundException;
import com.api.dev.finance_manager.model.Despesa;
import com.api.dev.finance_manager.repositories.repository.DespesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class DespesaService {

    @Autowired
    private DespesaRepository despesaRepository;

    public List<Despesa> listar(){
        return this.despesaRepository.findAll();
    }

    public Despesa buscarForId(Long id) {
        if(id < 1){
            throw new IllegalArgumentException("the id most be higher or equals 1");
        }
        return this.despesaRepository.findById(id).orElseThrow(() -> new DespesaNotFoundException());
    }

    public void deletarById(Long id){
        if(id < 1){
            throw new IllegalArgumentException("the id most be higher or equals 1");
        }
        Despesa despesa = this.despesaRepository.findById(id).orElseThrow(() -> new DespesaNotFoundException());
        this.despesaRepository.deleteById(id);
    }

    public Despesa salvar(Despesa despesa){
        return this.despesaRepository.save(despesa);
    }

    public void alterar(Despesa despesa, Long id){
        Optional<Despesa> despesaBuscada = this.despesaRepository.findById(id);
        if(despesaBuscada.isPresent()){
            this.despesaRepository.updateDespesa(id,despesa);
        }
        else{
            throw new DespesaNotFoundException();
        }
    }

    public Page<Despesa> find(Long id, String destino, Date data, DespesaStatus despesaStatus, DespesaCategoria despesaCategoria, Pageable pageable){
        if(id != null && id < 1){
            throw new IllegalArgumentException("the id most be higher or equals 1");
        }
        return this.despesaRepository.find(id, destino, data, despesaStatus, despesaCategoria, pageable);

    }
}
