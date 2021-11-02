package com.api.dev.finance_manager.controllers;

import com.api.dev.finance_manager.model.Despesa;
import com.api.dev.finance_manager.services.DespesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(path = "/despesas")
public class DespesaController {

    @Autowired
    private DespesaService despesaService;

    @GetMapping
    public ResponseEntity<List<Despesa>> listar(){
        List<Despesa> listaDespesas = this.despesaService.listar();
        return ResponseEntity.ok(listaDespesas);
    }

    @GetMapping(path = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> buscarForId(@PathVariable("id") Long id){
        try{
            Despesa despesaBuscada = this.despesaService.buscarForId(id);
            return ResponseEntity.ok(despesaBuscada);
        }
        catch (NoSuchElementException errorNotFound){
            return ResponseEntity.notFound().build();
        }
        catch (IllegalArgumentException errorIllegalArgument){
            return ResponseEntity.badRequest().body(errorIllegalArgument.getMessage());
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteForId(@PathVariable("id") Long id){
        try{
            this.despesaService.deletarById(id);
            return ResponseEntity.noContent().build();
        }
        catch (NoSuchElementException errorNotFound){
            return ResponseEntity.notFound().build();
        }
        catch (IllegalArgumentException illegalArgumentException){
            return ResponseEntity.badRequest().body(illegalArgumentException.getMessage());
        }
    }

}
