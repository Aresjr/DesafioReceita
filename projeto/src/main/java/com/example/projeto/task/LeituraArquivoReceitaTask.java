package com.example.projeto.task;

import com.example.projeto.builder.ArquivoReceitaBuilder;
import com.example.projeto.model.ContaReceita;
import com.example.projeto.service.ReceitaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

@Component
public class LeituraArquivoReceitaTask {
    private static final Logger log = LoggerFactory.getLogger(LeituraArquivoReceitaTask.class);

    @Autowired
    private ReceitaService receitaService;

    @Autowired
    private MessageSource messageSource;

    @Scheduled(fixedRate = 10000)
    public void enviaContasReceita() {

        String caminhoArquivoLeitura = messageSource.getMessage("arquivo.caminho.leitura", null, Locale.getDefault());
        ArquivoReceitaBuilder arquivoBuilder = new ArquivoReceitaBuilder(messageSource);
        List<ContaReceita> contas = arquivoBuilder.trazContasArquivo(caminhoArquivoLeitura);

        log.info(messageSource.getMessage("log.arquivo.iniciando.envio", null, Locale.getDefault()));
        for(ContaReceita conta : contas){
            receitaService.atualizarConta(conta);
            log.info(messageSource.getMessage("log.arquivo.conta.conteudo", new String[]{conta.toString()}, Locale.getDefault()));
        }
        log.info(messageSource.getMessage("log.arquivo.conta.envio.finalizado", null, Locale.getDefault()));


        String caminhoArquivoEscrita = messageSource.getMessage("arquivo.caminho.escrita", null, Locale.getDefault());
        try {
            arquivoBuilder.gravaContasArquivo(caminhoArquivoEscrita, contas);
            log.info(messageSource.getMessage("log.arquivo.escrita.fim", new String[]{caminhoArquivoEscrita}, Locale.getDefault()));
        } catch (IOException e) {
            log.error(messageSource.getMessage("log.arquivo.nao.criado", new String[]{caminhoArquivoEscrita}, Locale.getDefault()));
        }

    }
}