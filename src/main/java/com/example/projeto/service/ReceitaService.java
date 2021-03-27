package com.example.projeto.service;

import com.example.projeto.model.ContaReceita;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("singleton")
public class ReceitaService {

    public Boolean atualizarConta(String agencia, String conta, Float saldo, Character status){
        //envio fake/mock
        return true;
    }

    public Boolean atualizarConta(ContaReceita conta){
        //envio fake/mock
        conta.setResultado(true);
        return true;
    }

}
