package com.api.dev.finance_manager.model;

import com.api.dev.finance_manager.enums.DespesaCategoria;
import com.api.dev.finance_manager.enums.DespesaStatus;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "despesa")
public class Despesa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 50, nullable = false)
    private String destino;

    @Column(nullable = false)
    private Date data;

    @Column(nullable = false,name="status",length = 10)
    @Enumerated(EnumType.STRING)
    private DespesaStatus despesaStatus;

    @Column(nullable = false,name = "categoria",length = 10)
    @Enumerated(EnumType.STRING)
    private DespesaCategoria despesaCategoria;


    public Despesa() {
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
