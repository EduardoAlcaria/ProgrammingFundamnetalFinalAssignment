package com.alcaria.eduardo.domain;

import java.util.Scanner;

public class Orcamento {
    private Viagem viagem;
    private static double totalGasto;

    public Orcamento(Viagem viagem) {
        this.viagem = viagem;
    }
    public static void calcTotal(Atividade[] atividades, Acomodacao[] acomodacoes, Viagem viagem) {

        for (Atividade atividade : atividades) {
            if (atividade.getViagem().getDestino().equalsIgnoreCase(viagem.getDestino())){
                totalGasto += atividade.getCustoEstimado();
            }
        }

        for (Acomodacao acomodacao : acomodacoes) {
            if(acomodacao.getViagem().getDestino().equalsIgnoreCase(viagem.getDestino())){
                totalGasto += acomodacao.calcTotal();
            }
        }
        setTotalGasto(totalGasto);

    }

    public static double getTotalGasto(Atividade[] atividadesCopia,Acomodacao[] acomodacoesCopia, Viagem viagemEscolhida) {
        Orcamento.calcTotal(atividadesCopia, acomodacoesCopia, viagemEscolhida);
        return totalGasto;
    }

    public static void setTotalGasto(double totalGasto) {
        Orcamento.totalGasto = totalGasto;
    }
}
