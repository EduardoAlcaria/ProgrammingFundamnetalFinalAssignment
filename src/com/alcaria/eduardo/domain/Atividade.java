package com.alcaria.eduardo.domain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Atividade {
    private int id;
    private String descricao;
    private double custoEstimado;
    private Viagem viagem;
    private String data;

    public Atividade(int id, String descricao, double custoEstimado, Viagem viagem, String data) {
        this.id = id;
        this.descricao = descricao;
        this.custoEstimado = custoEstimado;
        this.viagem = viagem;
        this.data = data;
        formatterUnico(data);
    }


    public static double calcAtividades(Atividade[] atividades){
        double custoTotal = 0;
        for (Atividade atividade : atividades) {
            custoTotal += atividade.getCustoEstimado();
        }
        return custoTotal;
    }

    public void formatterUnico(String data) {
        if (data == null) {
            System.out.println("A data esta vazia.");
            return;
        }

        try {
            String formatted = data.replaceAll("-", "/");

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            LocalDate dataFormatada = LocalDate.parse(formatted, formatter);

            this.data = String.valueOf(dataFormatada);

        } catch (Exception e) {
            System.out.println("Erro ao formatar datas. Use o padrão dd-MM-yyyy.");
        }
    }


    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getCustoEstimado() {
        return custoEstimado;
    }

    public void setCustoEstimado(double custoEstimado) {
        this.custoEstimado = custoEstimado;
    }

    public Viagem getViagem() {
        return viagem;
    }

    public void setViagem(Viagem viagem) {
        this.viagem = viagem;
    }

}
