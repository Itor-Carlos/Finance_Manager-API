package com.api.dev.finance_manager.controllers;

import com.api.dev.finance_manager.enums.DespesaStatus;
import com.api.dev.finance_manager.model.Despesa;
import com.api.dev.finance_manager.services.DespesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import sun.security.krb5.internal.crypto.Des;


import javax.print.attribute.standard.Media;
import java.net.URI;
import java.util.Date;
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

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> salvar(@RequestBody Despesa despesa){
        try{
            Despesa despesaSalva = this.despesaService.salvar(despesa);
            URI despesaSalvaLocation = URI.create("/despesas/"+despesaSalva.getId());
            return ResponseEntity.created(despesaSalvaLocation).body(despesaSalva);
        }
        catch (IllegalArgumentException illegalArgumentException){
            return ResponseEntity.badRequest().body(illegalArgumentException.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> alterar(@PathVariable("id") Long id, @RequestBody Despesa despesa){
        try{

            Despesa despesaAlterada = this.despesaService.alterar(despesa,id);
            return ResponseEntity.ok(despesaAlterada);
        }
        catch (NoSuchElementException elementException){
            return ResponseEntity.notFound().build();
        }
        catch (IllegalArgumentException illegalArgumentException){
            return ResponseEntity.badRequest().body(illegalArgumentException.getMessage());
        }
    }

    @GetMapping("/find")
    public ResponseEntity<?> find(@Param("id") Long id, @Param("destino") String destino, @Param("data") Date data, @Param("despesaStatus")DespesaStatus despesaStatus){
        try{
            List<Despesa> listaResultado = this.despesaService.find(id,destino,data,despesaStatus);
            return ResponseEntity.ok(listaResultado);
        }
        catch (IllegalArgumentException errorIllegalArgument){
            return ResponseEntity.badRequest().body(errorIllegalArgument.getMessage());
        }
    }
}
