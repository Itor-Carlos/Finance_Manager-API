package com.api.dev.finance_manager.model;

import com.api.dev.finance_manager.enums.DespesaCategoria;
import com.api.dev.finance_manager.enums.DespesaStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "despesa")
@JsonPropertyOrder({"destino","data","status","categoria","links"})
public class Despesa extends RepresentationModel<Despesa>{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    @Column(length = 50, nullable = false)
    private String destino;

    @Column(nullable = false)
    private Date data;

    @Column(nullable = false,name="status",length = 10)
    @Enumerated(EnumType.STRING)
    @JsonProperty(value = "status")
    private DespesaStatus despesaStatus;

    @Column(nullable = false,name = "categoria",length = 10)
    @Enumerated(EnumType.STRING)
    @JsonProperty(value = "categoria")
    private DespesaCategoria despesaCategoria;


    public Despesa() {
    }

    public Despesa(String destino, Date data, DespesaStatus despesaStatus, DespesaCategoria despesaCategoria) {
        this.destino = destino;
        this.data = data;
        this.despesaStatus = despesaStatus;
        this.despesaCategoria = despesaCategoria;
    }

    public Despesa(Long id, String destino, Date data, DespesaStatus despesaStatus, DespesaCategoria despesaCategoria) {
        this.id = id;
        this.destino = destino;
        this.data = data;
        this.despesaStatus = despesaStatus;
        this.despesaCategoria = despesaCategoria;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public DespesaStatus getDespesaStatus() {
        return despesaStatus;
    }

    public void setDespesaStatus(DespesaStatus despesaStatus) {
        this.despesaStatus = despesaStatus;
    }

    public DespesaCategoria getDespesaCategoria() {
        return despesaCategoria;
    }

    public void setDespesaCategoria(DespesaCategoria despesaCategoria) {
        this.despesaCategoria = despesaCategoria;
    }
}
