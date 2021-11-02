package com.api.dev.finance_manager.controllers;

import com.api.dev.finance_manager.services.DespesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/")
public class DespesaController {

    @Autowired
    private DespesaService despesaService;

}
