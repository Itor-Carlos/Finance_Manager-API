package com.api.dev.finance_manager.services;

import com.api.dev.finance_manager.repositories.DespesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DespesaService {

    @Autowired
    private DespesaRepository despesaRepository;


}
