package com.alcaria.eduardo.domain;

public class Acomodacao {
    private int id;
    private String name;
    private double custoDiario;
    private int diasReservados;
    private Viagem viagem;

    public Acomodacao(int id, String name, double custoDiario, int diasReservados, Viagem viagem) {
        this.id = id;
        this.name = name;
        this.custoDiario = custoDiario;
        this.diasReservados = diasReservados;
        this.viagem = viagem;
    }

    public double calcTotal(){
        return custoDiario * diasReservados;
    }

    @Override
    public String toString() {
        return name + " (" + viagem.getDestino() + "), " + diasReservados + " dias reservados, R$" + calcTotal();
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCustoDiario() {
        return custoDiario;
    }

    public void setCustoDiario(double custoDiario) {
        this.custoDiario = custoDiario;
    }

    public int getDiasReservados() {
        return diasReservados;
    }

    public void setDiasReservados(int diasReservados) {
        this.diasReservados = diasReservados;
    }

    public Viagem getViagem() {
        return viagem;
    }

    public void setViagem(Viagem viagem) {
        this.viagem = viagem;
    }
}
