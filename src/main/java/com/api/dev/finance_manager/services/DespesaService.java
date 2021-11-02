package com.api.dev.finance_manager.services;

import com.api.dev.finance_manager.model.Despesa;
import com.api.dev.finance_manager.repositories.DespesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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

    public Despesa buscar(Long id) {
        if(id < 1){
            throw new IllegalArgumentException("the id most be higher or equals 1");
        }
        Optional<Despesa> todo = this.despesaRepository.findById(id);
        if(!todo.isPresent()){
            throw new NoSuchElementException("No value present in this id");
        }
        return todo.get();
    }
}
