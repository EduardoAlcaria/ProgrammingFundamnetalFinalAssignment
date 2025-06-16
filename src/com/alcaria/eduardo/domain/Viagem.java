package com.alcaria.eduardo.domain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Viagem {
    private int id;
    private String destino;
    private String dataInicio;
    private String dataFim;
    private Usuario usuario;

    public Viagem(String destino, Usuario usuario, String dataInicio, String dataFim) {
        this.destino = destino;
        this.usuario = usuario;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    public int calcDuracao(){
        try {
            if (dataInicio == null || dataFim == null) {
                throw new IllegalArgumentException("Datas não podem ser nulas.");
            }

            String formattedDataInicio = dataInicio.replaceAll("-", "/");
            String formattedDataFim = dataFim.replaceAll("-", "/");

            System.out.println("Data Início formatada: " + formattedDataInicio);
            System.out.println("Data Fim formatada: " + formattedDataFim);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            LocalDate dateInicio = LocalDate.parse(formattedDataInicio, formatter);
            LocalDate dateFim = LocalDate.parse(formattedDataFim, formatter);

            if (dateFim.isBefore(dateInicio)) {
                throw new IllegalArgumentException("Data final não pode ser antes da data inicial.");
            }

            return (int) ChronoUnit.DAYS.between(dateInicio, dateFim);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean formatter(String dataInicio, String dataFim) {
        if (dataInicio == null || dataFim == null) {
            System.out.println("Uma das datas está vazia.");
            return false;
        }

        try {
            String formattedInicio = dataInicio.replaceAll("-", "/");
            String formattedFim = dataFim.replaceAll("-", "/");

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            LocalDate inicio = LocalDate.parse(formattedInicio, formatter);
            LocalDate fim = LocalDate.parse(formattedFim, formatter);

            if (fim.isBefore(inicio)) {
                System.out.println("Data final não pode ser antes da data inicial.");
                return false;
            }

            return true;
        } catch (Exception e) {
            System.out.println("Erro ao formatar datas. Use o padrão dd-MM-yyyy.");
            return false;
        }
    }


    public String getDestino() {
        return destino;
    }

    public String getDataInicio() {
        return dataInicio;
    }


    public String getDataFim() {
        return dataFim;
    }


    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
