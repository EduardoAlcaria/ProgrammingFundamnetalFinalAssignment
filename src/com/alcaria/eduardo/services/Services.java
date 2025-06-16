package com.alcaria.eduardo.services;

import com.alcaria.eduardo.domain.*;

import java.util.Scanner;

public class Services {
    private static Scanner scanner = new Scanner(System.in);
    private static Usuario[] newUsers;
    private static Atividade[] newAtividade;
    private static Acomodacao[] newAcomodacoes;

    public static Usuario[] cadastroDeUser(Usuario[] usuarios, Usuario usuario) {
        newUsers = new Usuario[usuarios.length + 1];

        for (int i = 0; i < usuarios.length; i++) {
            String name = usuarios[i].getName().toLowerCase();
            String email = usuarios[i].getEmail().toLowerCase();

            if (name.equalsIgnoreCase(usuario.getName()) || email.equalsIgnoreCase(usuario.getEmail())) {
                System.out.println("the user already exists");
                return usuarios;
            }
        }


        for (int i = 0; i < usuarios.length; i++) {
            newUsers[i] = usuarios[i];
        }


        newUsers[usuarios.length] = usuario;
        return newUsers;
    }

    public static void cadastroViagem(Usuario usuario, Viagem viagem) {
        viagem.setUsuario(usuario);
        System.out.println("Viagem cadastrada para: " + usuario.getName());
    }

    public static Atividade[] adicionarAcomodacao(Atividade[] atividades, String nomeHotel, double precoNoite, int noites, Viagem viagem) {
        Atividade[] novaLista = new Atividade[atividades.length + 1];


        String descricao = "Acomodação: " + nomeHotel;
        for (int i = 0; i < atividades.length; i++) {
            if (atividades[i].getDescricao().equalsIgnoreCase(descricao)) {
                System.out.println("Essa acomodação já foi cadastrada.");
                return atividades;
            }
        }


        for (int i = 0; i < atividades.length; i++) {
            novaLista[i] = atividades[i];
        }

        double custoTotal = precoNoite * noites;
        int novoId = atividades.length + 1;

        Atividade novaAcomodacao = new Atividade(novoId, descricao, custoTotal, viagem);

        novaLista[atividades.length] = novaAcomodacao;

        System.out.println("Acomodação adicionada com sucesso.");

        return novaLista;
    }


    public static Viagem[] arrayFillViagem(Viagem[] viagens, Viagem viagem, Usuario user) {
        Viagem[] newViagem = new Viagem[viagens.length + 1];
        String op;
        for (int i = 0; i < viagens.length; i++) {
            String destino = viagens[i].getDestino().toLowerCase();

            if (destino.equalsIgnoreCase(viagem.getDestino())) {

                System.out.println("o destino ja existe, gostaria de gostaria de cadastrar outra[1], ou assegnar ao user[2]? (1/2)");
                op = scanner.nextLine();
                if (op.charAt(0) == '1') {
                    Interface.registrarViagem();
                } else if (op.charAt(0) == '2') {
                    cadastroViagem(user, viagem);
                }
            }

        }


        for (int i = 0; i < viagens.length; i++) {
            newViagem[i] = viagens[i];
        }


        newViagem[viagens.length] = viagem;
        return newViagem;

    }


    public static Atividade[] arrayFillAtividades(Atividade[] atividades, Atividade atividade) {
        newAtividade = new Atividade[atividades.length + 1];

        for (int i = 0; i < atividades.length; i++) {
            String desc = atividades[i].getDescricao().toLowerCase();

            if (desc.equalsIgnoreCase(atividade.getDescricao())) {
                System.out.println("the user already exists");
                return atividades;
            }
        }


        for (int i = 0; i < atividades.length; i++) {
            newAtividade[i] = atividades[i];
        }


        newAtividade[atividades.length] = atividade;
        return newAtividade;
    }

    public static Acomodacao[] arrayFillAcomodacoes(Acomodacao[] acomodacoes, Acomodacao acomodacao) {
        newAcomodacoes = new Acomodacao[acomodacoes.length + 1];

        for (int i = 0; i < acomodacoes.length; i++) {
            String nome = acomodacoes[i].getName().toLowerCase();
            if (nome.equalsIgnoreCase(acomodacao.getName())) {
                System.out.println("Acomodação já cadastrada.");
                return acomodacoes;
            }
            newAcomodacoes[i] = acomodacoes[i];
        }

        newAcomodacoes[acomodacoes.length] = acomodacao;
        return newAcomodacoes;
    }

    public static void consultarItinerario(Atividade[] atividades, Viagem[] viagens, Acomodacao[] acomodacoes, Scanner scanner) {
        String acomodacaoDestino = null;
        System.out.print("Nome da viagem: ");
        String destino = scanner.nextLine();

        for (Acomodacao acomodacao : acomodacoes) {
            if (acomodacao.getViagem().getDestino().equalsIgnoreCase(destino)){
                acomodacaoDestino = acomodacao.getName();
            }
        }


        for (Viagem viagem : viagens) {
            if (viagem.getDestino().equalsIgnoreCase(destino)) {
                System.out.println("Atividades da " + destino + ":");
                for (Atividade a : atividades) {
                    System.out.println("- " + a.getDescricao() + " (" + a.getViagem().getDestino() + ") " +
                            " (" + acomodacaoDestino +") ");
                }
                return;
            }
        }

        System.out.println("Viagem não encontrada.");
    }


}
