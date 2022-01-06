package com.api.dev.finance_manager.dto;

import com.api.dev.finance_manager.enums.DespesaCategoria;
import com.api.dev.finance_manager.enums.DespesaStatus;
import com.api.dev.finance_manager.model.Despesa;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class DespesaDTO {

    @NotBlank(message = "The destino cannot be null")
    private String destino;
    @NotNull(message = "The date cannot be null")
    private Date data;
    @NotNull(message = "The despesaStatus cannot be null")
    private DespesaStatus despesaStatus;
    @NotNull(message = "The despesaCategoria cannot be null")
    private DespesaCategoria despesaCategoria;

    public DespesaDTO() {
    }

    public DespesaDTO(String destino, Date data, DespesaStatus despesaStatus, DespesaCategoria despesaCategoria) {
        this.destino = destino;
        this.data = data;
        this.despesaStatus = despesaStatus;
        this.despesaCategoria = despesaCategoria;
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

    public Despesa toDespesa(){
        return new Despesa(this.getDestino(),this.getData(),this.getDespesaStatus(),this.getDespesaCategoria());
    }
}
