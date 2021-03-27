package com.example.projeto.interf;

import com.example.projeto.model.ContaReceita;

import java.util.List;

public interface ArquivoBuilderInterface {
    List<ContaReceita> trazContasArquivo(String caminhoArquivoLeitura);
    void gravaContasArquivo(String caminhoArquivoEscrita, List<ContaReceita> contas);
}
