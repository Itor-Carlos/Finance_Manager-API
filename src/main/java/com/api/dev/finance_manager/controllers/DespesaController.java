package com.api.dev.finance_manager.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


import com.api.dev.finance_manager.dto.DespesaDTO;
import com.api.dev.finance_manager.enums.DespesaCategoria;
import com.api.dev.finance_manager.enums.DespesaStatus;
import com.api.dev.finance_manager.exceptions.DespesaFieldNotValidExceptionDetails;
import com.api.dev.finance_manager.exceptions.DespesaNotFoundException;
import com.api.dev.finance_manager.exceptions.DespesaNotFoundExceptionDetails;
import com.api.dev.finance_manager.exceptions.IllegalArgumentExceptionDetails;
import com.api.dev.finance_manager.model.Despesa;
import com.api.dev.finance_manager.services.DespesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import javax.validation.Valid;
import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(path = "/despesas")
public class DespesaController {

    @Autowired
    private DespesaService despesaService;

    @Operation(summary = "Search a specificy despesa using id as parameter")//Descrevi the operation
    @ApiResponses(value = {//this annotation maps the status code, the description, the media type, and schema used. This information is provided in Swagger Ui, in the specific method

        @ApiResponse(responseCode = "200", description = "Found the despesa", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = DespesaDTO.class)) }),

        @ApiResponse(responseCode = "404", description = "Not Found the despesa", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = DespesaNotFoundExceptionDetails.class)) }),

        @ApiResponse(responseCode = "400", description = "Id passed its not valid", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = IllegalArgumentExceptionDetails.class)) }),

    })
    @GetMapping(path = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> buscarForId(@PathVariable("id") Long id){
        Despesa despesaBuscada = this.despesaService.buscarForId(id);
        return ResponseEntity.ok(despesaBuscada);
    }

    @Operation(summary = "Delete a specifiy despesa using id as parameter")//describes the operation
    @ApiResponses(value = {//this annotation maps the status code, the description, the media type, and schema used. This information is provided in Swagger Ui, in the specific method
        @ApiResponse(responseCode = "204", description = "Deleted despesa", content = { @Content(mediaType = "application/json") }),

        @ApiResponse(responseCode = "400", description = "Id passed its not valid", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = IllegalArgumentExceptionDetails.class)) }),

        @ApiResponse(responseCode = "404", description = "Not found the despesa. Operation cannot be completed", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = DespesaNotFoundExceptionDetails.class)) }),
        
    })
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteForId(@PathVariable("id") Long id){
        this.despesaService.deletarById(id);
        return ResponseEntity.noContent().build();
    }
    
    @Operation(summary = "Create despesa in the database")//describes the operation
    @ApiResponses(value = {//this annotation maps the status code, the description, the media type, and schema used. This information is provided in Swagger Ui, in the specific method
        @ApiResponse(responseCode = "201", description = "Created despesa", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = DespesaDTO.class)) }),

        @ApiResponse(responseCode = "400", description = "Despesa Field Not Valid. Operation cannot be completed", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = DespesaFieldNotValidExceptionDetails.class)) }),        
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> salvar(@RequestBody @Valid DespesaDTO despesaDTO){
        Despesa despesaSalva = this.despesaService.salvar(despesaDTO.toDespesa());
        URI despesaSalvaLocation = URI.create("/despesas/"+despesaSalva.getId());
        return ResponseEntity.created(despesaSalvaLocation).body(despesaSalva);
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> alterar(@PathVariable("id") Long id, @RequestBody DespesaDTO despesaDTO){
        this.despesaService.alterar(despesaDTO.toDespesa(),id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> list(@RequestParam(name = "id", required = false) Long id, @RequestParam(name = "destino",required = false) String destino, @RequestParam(name = "data", required = false) Date data, @RequestParam(name = "despesaStatus",required = false)DespesaStatus despesaStatus, @RequestParam(name = "despesaCategoria",required = false)DespesaCategoria despesaCategoria, Pageable pageable){
        Page pageResult = this.despesaService.find(id, destino, data, despesaStatus, despesaCategoria, pageable);
        List<Despesa> listResult = pageResult.getContent();
        for(Despesa element: listResult){
            long idDespesa = element.getId();
            element.add(linkTo(methodOn(DespesaController.class).buscarForId(idDespesa)).withSelfRel());
        }
        pageResult = new PageImpl<>(listResult, pageable, listResult.size());
        return ResponseEntity.ok(pageResult);
    }
}
