package com.api.dev.finance_manager.services;

import com.api.dev.finance_manager.enums.DespesaCategoria;
import com.api.dev.finance_manager.enums.DespesaStatus;
import com.api.dev.finance_manager.model.Despesa;
import com.api.dev.finance_manager.repositories.repository.DespesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        return this.despesaRepository.findById(id).orElseThrow(() -> new NoSuchElementException());
    }

    public void deletarById(Long id){
        if(id < 1){
            throw new IllegalArgumentException("the id most be higher or equals 1");
        }
        Despesa despesa = this.despesaRepository.findById(id).orElseThrow(() -> new NoSuchElementException());
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

    public void alterar(Despesa despesa, Long id){
        Optional<Despesa> despesaBuscada = this.despesaRepository.findById(id);
        if(despesaBuscada.isPresent()){
            this.despesaRepository.updateDespesa(id,despesa);
        }
        else{
            throw new NoSuchElementException();
        }
    }

    public List<Despesa> find(Long id, String destino, Date data, DespesaStatus despesaStatus, DespesaCategoria despesaCategoria){
        if(id != null && id < 1){
            throw new IllegalArgumentException("the id most be higher or equals 1");
        }
        return this.despesaRepository.find(id,destino,data,despesaStatus,despesaCategoria);
    }
}
