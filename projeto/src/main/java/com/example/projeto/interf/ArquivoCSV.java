package com.example.projeto.interf;

import java.util.List;

public interface ArquivoCSV {
    String getConteudo();
    void setConteudo(String conteudo);
    List<String> getLinhas();
}
