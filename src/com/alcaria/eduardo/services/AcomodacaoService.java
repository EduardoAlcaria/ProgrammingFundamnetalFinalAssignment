package com.alcaria.eduardo.services;

import com.alcaria.eduardo.domain.Acomodacao;
import com.alcaria.eduardo.domain.Viagem;

import static com.alcaria.eduardo.services.InputHandler.pressioneParaContinuar;


public class AcomodacaoService {
    public static Acomodacao[] acomodacoesCopia = new Acomodacao[0];

    public static void adicionarAcomodacao(String nome, double preco, int dias, Viagem viagem) {

        Acomodacao acomodacao = new Acomodacao(acomodacoesCopia.length, nome, preco, dias, viagem);
        acomodacoesCopia = GeralServices.arrayFillAcomodacoes(acomodacoesCopia, acomodacao);

        System.out.println("Acomodação adicionada com sucesso!");
        pressioneParaContinuar();
    }
}
