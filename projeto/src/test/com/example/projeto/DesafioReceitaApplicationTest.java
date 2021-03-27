package com.example.projeto;

import com.example.projeto.builder.ArquivoReceitaBuilder;
import com.example.projeto.model.ContaReceita;
import com.example.projeto.service.ReceitaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class DesafioReceitaApplicationTest {

    @Autowired
    private ReceitaService receitaService;

    @Autowired
    private MessageSource messageSource;

    @Test
    public void singletonInstanciado() {
        assertNotNull(receitaService);
    }

    @Test
    public void arquivoExiste() {
        //remover ou renomear arquivo para que teste não passe
        String caminhoArquivoLeitura = messageSource.getMessage("arquivo.caminho.leitura", null, Locale.getDefault());
        File arquivo = new File(caminhoArquivoLeitura);
        assertTrue(arquivo.exists(), messageSource.getMessage("log.arquivo.nao.encontrado", new String[]{caminhoArquivoLeitura}, Locale.getDefault()));
    }

    @Test
    public void arquivoSaidaGravavel() {

        //alterar o caminho para C:\\arquivo_processado.csv para estourar java.io.FileNotFoundException: C:\arquivo_processado.csv (Access is denied)
        ArquivoReceitaBuilder arquivoReceitaBuilder = new ArquivoReceitaBuilder(messageSource);
        String caminhoArquivoEscrita = messageSource.getMessage("arquivo.caminho.escrita", null, Locale.getDefault());

        assertDoesNotThrow(() -> arquivoReceitaBuilder.gravaContasArquivo(caminhoArquivoEscrita, getContasDummy()));
    }

    private static List<ContaReceita> getContasDummy(){
        //TODO - migrar para um mockito
        List<ContaReceita> contas = new ArrayList<>();
        contas.add(new ContaReceita("0000", "0000-1", 1.23f, 'A'));
        contas.add(new ContaReceita("1111", "1111-2", 2.34f, 'I'));
        return contas;
    }

}
