package com.example.projeto.model;

import com.example.projeto.interf.ImportavelCsv;

public class ContaReceita implements ImportavelCsv {

    private String agencia;
    private String conta;
    private Float saldo;
    private Character status;
    private Boolean resultado;
    private final static String[] header = {"agencia", "conta", "saldo", "status", "resultado"};

    public ContaReceita(String agencia, String conta, Float saldo, Character status){
        this.agencia = agencia;
        this.conta = conta;
        this.saldo = saldo;
        this.status = status;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public Float getSaldo() {
        return saldo;
    }

    public void setSaldo(Float saldo) {
        this.saldo = saldo;
    }

    public Character getStatus() {
        return status;
    }

    public void setStatus(Character status) {
        this.status = status;
    }

    public Boolean getResultado() {
        return resultado;
    }

    public void setResultado(Boolean resultado) {
        this.resultado = resultado;
    }

    @Override
    public String toString() {
        return "ContaReceita{" +
                "agencia='" + agencia + '\'' +
                ", conta='" + conta + '\'' +
                ", saldo=" + saldo +
                ", status=" + status +
                ", resultado=" + resultado +
                '}';
    }

    public String[] toArray(){
        return new String[]{agencia, conta, String.format("%.02f", saldo).replace('.', ','), status.toString(), resultado ? "1" : "0"};
    }

    public static String[] getHeader(){
        return header;
    }

}
