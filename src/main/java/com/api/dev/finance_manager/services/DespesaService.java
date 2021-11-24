package com.api.dev.finance_manager.services;

import com.api.dev.finance_manager.model.Despesa;
import com.api.dev.finance_manager.repositories.repository.DespesaRepository;
import org.springframework.beans.BeanUtils;
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

    public Despesa buscarForId(Long id) {
        if(id < 1){
            throw new IllegalArgumentException("the id most be higher or equals 1");
        }
        Optional<Despesa> todo = this.despesaRepository.findById(id);
        if(!todo.isPresent()){
            throw new NoSuchElementException("No value present in this id");
        }
        return todo.get();
    }

    public void deletarById(Long id){
        if(id < 1){
            throw new IllegalArgumentException("the id most be higher or equals 1");
        }
        Optional<Despesa> todo = this.despesaRepository.findById(id);
        if(!todo.isPresent()){
            throw new NoSuchElementException("No value present in this id");
        }
        this.despesaRepository.deleteById(id);
    }

    public Despesa salvar(Despesa despesa){
        if(despesa.getDestino() == null){
            throw new IllegalArgumentException("the destiny cannot be null");
        }
        if(despesa.getDestino() == ""){
            throw new IllegalArgumentException("the destiny cannot be empty");
        }
        if(despesa.getData() == null) {
            throw new IllegalArgumentException("the data cannot be null");
        }

        return this.despesaRepository.save(despesa);
    }

    public Despesa alterar(Despesa despesa, Long id){
        Optional<Despesa> despesaBuscada = this.despesaRepository.findById(id);
        if(!despesaBuscada.isPresent()){
            throw new NoSuchElementException("No value present in this id");
        }
        if(despesa.getDestino() == null){
            throw new IllegalArgumentException("the destiny cannot be null");
        }
        if(despesa.getDestino() == ""){
            throw new IllegalArgumentException("the destiny cannot be empty");
        }
        if(despesa.getData() == null) {
            throw new IllegalArgumentException("the data cannot be null");
        }

        BeanUtils.copyProperties(despesa,despesaBuscada.get(),"id");
        return this.despesaRepository.save(despesaBuscada.get());

    }
}
