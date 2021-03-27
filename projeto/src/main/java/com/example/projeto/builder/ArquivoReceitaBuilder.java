package com.example.projeto.builder;

import com.example.projeto.interf.ArquivoBuilderInterface;
import com.example.projeto.model.ContaReceita;
import com.opencsv.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ArquivoReceitaBuilder implements ArquivoBuilderInterface {

    private static final Logger log = LoggerFactory.getLogger(ArquivoReceitaBuilder.class);
    private final MessageSource messageSource;

    public ArquivoReceitaBuilder(MessageSource messageSource){
        this.messageSource = messageSource;
    }

    @Override
    public List<ContaReceita> trazContasArquivo(String caminhoArquivoLeitura) throws IOException {
        CSVParser parser = new CSVParserBuilder().withSeparator(';').build();

        log.info(messageSource.getMessage("log.arquivo.lendo", new String[]{caminhoArquivoLeitura}, Locale.getDefault()));
        FileReader fileReader = new FileReader(caminhoArquivoLeitura);
        CSVReader csvReader = new CSVReaderBuilder(fileReader).withSkipLines(1).withCSVParser(parser).build();
        List<ContaReceita> contasReceita = this.parseLines(csvReader);
        csvReader.close();
        return contasReceita;
    }

    private List<ContaReceita> parseLines(CSVReader csvReader) throws IOException {
        List<ContaReceita> contas = new ArrayList<>();
        String[] line;
        while ((line = csvReader.readNext()) != null) {
            if(line.length != 4){
                log.error(messageSource.getMessage("log.arquivo.esperado.colunas", new Integer[]{line.length}, Locale.getDefault()));
                continue;
            }
            float saldo;
            try {
                saldo = Float.parseFloat(line[2].replaceAll(",","."));
            } catch(NumberFormatException e){
                log.error(messageSource.getMessage("log.arquivo.coluna.saldo.invalido", new String[]{line[2]}, Locale.getDefault()));
                continue;
            }
            if(line[3].length() != 1){
                log.error(messageSource.getMessage("log.arquivo.coluna.status.invalido", new String[]{line[3]}, Locale.getDefault()));
                continue;
            }
            contas.add(new ContaReceita(line[0], line[1], saldo, line[3].charAt(0)));
        }
        return contas;
    }

    @Override
    public void gravaContasArquivo(String caminhoArquivoEscrita, List<ContaReceita> contas) throws IOException {
        FileOutputStream fos = new FileOutputStream(caminhoArquivoEscrita);
        OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
        CSVWriter writer = new CSVWriter(osw, ';', CSVWriter.DEFAULT_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);
        List<String[]> linhas = new ArrayList<>();
        linhas.add(ContaReceita.getHeader());

        for(ContaReceita conta : contas){
            linhas.add(conta.toArray());
        }

        writer.writeAll(linhas, false);
        writer.close();
    }
}
