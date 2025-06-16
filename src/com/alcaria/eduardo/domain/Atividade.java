package com.alcaria.eduardo.domain;

public class Atividade {
    private int id;
    private String descricao;
    private double custoEstimado;
    private Viagem viagem;

    public Atividade(int id, String descricao, double custoEstimado, Viagem viagem) {
        this.id = id;
        this.descricao = descricao;
        this.custoEstimado = custoEstimado;
        this.viagem = viagem;
    }


    public static double calcAtividades(Atividade[] atividades){
        double custoTotal = 0;
        for (Atividade atividade : atividades) {
            custoTotal += atividade.getCustoEstimado();
        }
        return custoTotal;
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
