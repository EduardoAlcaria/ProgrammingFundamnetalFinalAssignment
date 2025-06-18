package com.alcaria.eduardo.services;

import com.alcaria.eduardo.domain.Atividade;
import com.alcaria.eduardo.domain.Viagem;

public class AtividadeService {
    public static Atividade[] atividadesCopia = new Atividade[0];

    public static void addAtividade(String desc, double custoEstimado, Viagem viagem, String data) {
        Atividade[] atividadesLocal;

        Atividade atividade = new Atividade(atividadesCopia.length, desc, custoEstimado, viagem, data);
        atividadesLocal = GeralServices.arrayFillAtividades(atividadesCopia, atividade);
        atividadesCopia = atividadesLocal;
    }
}

