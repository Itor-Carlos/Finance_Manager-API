package com.api.dev.finance_manager.enums;

public enum DespesaStatus {

    PENDENTE("pendente"),
    PAGA("paga");

    private String despesaStatus;

    DespesaStatus(String despesaStatus) {
        this.despesaStatus = despesaStatus;
    }

    public String getDespesaStatus() {
        return despesaStatus;
    }

    public void setDespesaStatus(String despesaStatus) {
        this.despesaStatus = despesaStatus;
    }
}
