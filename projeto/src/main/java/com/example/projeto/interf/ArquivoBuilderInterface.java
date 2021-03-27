package com.example.projeto.interf;

import com.example.projeto.model.ContaReceita;

import java.io.IOException;
import java.util.List;

public interface ArquivoBuilderInterface {
    List<ContaReceita> trazContasArquivo(String caminhoArquivoLeitura) throws IOException;
    void gravaContasArquivo(String caminhoArquivoEscrita, List<ContaReceita> contas) throws IOException;
}
