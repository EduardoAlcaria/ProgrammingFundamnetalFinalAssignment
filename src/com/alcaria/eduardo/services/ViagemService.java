package com.alcaria.eduardo.services;

import com.alcaria.eduardo.domain.Usuario;
import com.alcaria.eduardo.domain.Viagem;

import static com.alcaria.eduardo.services.InputHandler.*;
import static com.alcaria.eduardo.main.Interface.*;
import static com.alcaria.eduardo.services.UserServices.buscarUsuarioPorNome;

public class ViagemService {
    public static Viagem[] viagensCopia = new Viagem[0];

    public static void registrarViagem(String destino, String nomeUsuario, String dataInicio, String dataFim) {
        Usuario user = buscarUsuarioPorNome(nomeUsuario);

        if (user == null) {
            System.out.println("O usuário " + nomeUsuario + " não existe");
            int op = lerInteiro("Gostaria de tentar de novo[1], ou criar um usuário[2]? :> ");
            if (op == 1) {
                addViagemTela();
            } else if (op == 2) {
                addUserTela();
            }
            return;
        }

        if (!Viagem.formatter(dataInicio, dataFim)) {
            System.out.println("Data inválida. Use o formato dd-MM-yyyy e verifique a ordem.");
            return;
        }

        Viagem viagem = new Viagem(viagensCopia.length, destino, user, dataInicio, dataFim);
        viagensCopia = GeralServices.arrayFillViagem(viagensCopia, viagem, user);
        viagem.setUsuario(user);
        System.out.println("Viagem cadastrada para: " + user.getNome());
    }

    public static void consultarViagensPorUsuario(String nomeUsuario) {
        boolean achou = false;
        System.out.println("Viagens do usuário " + nomeUsuario + ": ");
        for (Viagem v : viagensCopia) {
            if (v.getUsuario().getNome().equalsIgnoreCase(nomeUsuario)) {
                System.out.println("- " + v.getDestino() + " (" + v.getDataInicio() + " a " + v.getDataFim() + ")");
                achou = true;
            }
        }

        if (!achou) {
            System.out.println("Nenhuma viagem encontrada para este usuário.");
        }
        pressioneParaContinuar();
    }

    public static Viagem escolhaDeViagem() {
        Viagem viagemEncontrada;

        if (viagensCopia == null || viagensCopia.length == 0) {
            boolean desejaCadastrar = simOuNao("Ops.. parece que nao temos nenhuma viagem registrada, gostaria de registrar uma?");
            if (desejaCadastrar) {
                addViagemTela();
            }
        }

        while (true) {
            System.out.println("Escolha uma das viagens registradas:");

            int[] displayToIndex = new int[viagensCopia.length];
            int c = 0;

            for (int i = 0; i < viagensCopia.length; i++) {
                boolean isDuplicate = false;

                for (int j = 0; j < i; j++) {
                    if (
                            viagensCopia[i].getDestino().equalsIgnoreCase(viagensCopia[j].getDestino()) &&
                                    viagensCopia[i].getDataInicio().equalsIgnoreCase(viagensCopia[j].getDataInicio())
                    ) {
                        isDuplicate = true;
                        break;
                    }
                }

                if (!isDuplicate) {
                    viagensCopia[i].setId(i);
                    System.out.println((c + 1) + " * " + viagensCopia[i].getDestino());
                    displayToIndex[c] = i;
                    c++;
                }
            }

            int numeroViagem = lerInteiro(":> ");

            if (numeroViagem < 1 || numeroViagem > c) {
                System.out.println("Viagem não encontrada, tente novamente.");
                continue;
            }

            int selectedIndex = displayToIndex[numeroViagem - 1];
            viagemEncontrada = viagensCopia[selectedIndex];
            break;
        }

        return viagemEncontrada;

    }
}
