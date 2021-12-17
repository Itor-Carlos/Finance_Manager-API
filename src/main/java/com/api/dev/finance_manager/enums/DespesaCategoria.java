package com.api.dev.finance_manager.enums;

public enum DespesaCategoria {

    EDUCACAO("educacao"),
    LAZER("lazer"),
    SAUDE("saude"),
    COMIDA("comida");

    private String categoria;

    DespesaCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
