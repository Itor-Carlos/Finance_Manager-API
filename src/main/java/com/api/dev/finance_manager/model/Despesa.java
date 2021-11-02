package com.api.dev.finance_manager.model;

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

    public Despesa() {
    }

    public Despesa(Long id, String destino, Date data) {
        this.id = id;
        this.destino = destino;
        this.data = data;
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
}
