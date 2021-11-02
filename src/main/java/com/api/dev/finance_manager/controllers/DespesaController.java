package com.api.dev.finance_manager.controllers;

import com.api.dev.finance_manager.model.Despesa;
import com.api.dev.finance_manager.services.DespesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.security.krb5.internal.crypto.Des;


import java.awt.geom.RectangularShape;
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

    @GetMapping("/{id}")
    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> buscar(@PathVariable("id") Long id){
        try{
            Despesa despesaBuscada = this.despesaService.buscar(id);
            return ResponseEntity.ok(despesaBuscada);
        }
        catch (NoSuchElementException errorNotFound){
            return ResponseEntity.notFound().build();
        }
        catch (IllegalArgumentException errorIllegalArgument){
            return ResponseEntity.badRequest().body(errorIllegalArgument.getMessage());
        }
    }

}
