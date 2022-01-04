package com.api.dev.finance_manager.controllers;

import com.api.dev.finance_manager.dto.DespesaDTO;
import com.api.dev.finance_manager.enums.DespesaCategoria;
import com.api.dev.finance_manager.enums.DespesaStatus;
import com.api.dev.finance_manager.exceptions.DespesaNotFoundException;
import com.api.dev.finance_manager.model.Despesa;
import com.api.dev.finance_manager.services.DespesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;


import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(path = "/despesas")
public class DespesaController {

    @Autowired
    private DespesaService despesaService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
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
        catch (DespesaNotFoundException errorNotFound){
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

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> salvar(@RequestBody DespesaDTO despesaDTO){
        try{
            Despesa despesaSalva = this.despesaService.salvar(despesaDTO.toDespesa());
            URI despesaSalvaLocation = URI.create("/despesas/"+despesaSalva.getId());
            return ResponseEntity.created(despesaSalvaLocation).body(despesaSalva);
        }
        catch (IllegalArgumentException illegalArgumentException){
            return ResponseEntity.badRequest().body(illegalArgumentException.getMessage());
        }
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> alterar(@PathVariable("id") Long id, @RequestBody DespesaDTO despesaDTO){
        try{
            this.despesaService.alterar(despesaDTO.toDespesa(),id);
            return ResponseEntity.ok().build();
        }
        catch (NoSuchElementException elementException){
            return ResponseEntity.notFound().build();
        }
        catch (IllegalArgumentException illegalArgumentException){
            return ResponseEntity.badRequest().body(illegalArgumentException.getMessage());
        }
    }

    @GetMapping(path = "/find", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> find(@RequestParam(name = "id", required = false) Long id, @RequestParam(name = "destino",required = false) String destino, @RequestParam(name = "data", required = false) Date data, @RequestParam(name = "despesaStatus",required = false)DespesaStatus despesaStatus, @RequestParam(name = "despesaCategoria",required = false)DespesaCategoria despesaCategoria){
        try{
            List<Despesa> listaResultado = this.despesaService.find(id,destino,data,despesaStatus,despesaCategoria);
            return ResponseEntity.ok(listaResultado);
        }
        catch (IllegalArgumentException errorIllegalArgument){
            return ResponseEntity.badRequest().body(errorIllegalArgument.getMessage());
        }
    }
}
