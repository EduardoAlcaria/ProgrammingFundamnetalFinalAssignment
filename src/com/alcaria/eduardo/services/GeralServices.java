package com.alcaria.eduardo.services;

import com.alcaria.eduardo.domain.*;

public class GeralServices {
    private static Usuario[] newUsers;
    private static Atividade[] newAtividade;
    private static Acomodacao[] newAcomodacoes;

    public static Usuario[] arrayFillUser(Usuario[] usuarios, Usuario usuario) {
        newUsers = new Usuario[usuarios.length + 1];

        for (int i = 0; i < usuarios.length; i++) {
            String name = usuarios[i].getNome().toLowerCase();
            String email = usuarios[i].getEmail().toLowerCase();

            if (name.equalsIgnoreCase(usuario.getNome()) || email.equalsIgnoreCase(usuario.getEmail())) {
                System.out.println("o user ja existe");
                return usuarios;
            }
        }


        for (int i = 0; i < usuarios.length; i++) {
            newUsers[i] = usuarios[i];
        }


        newUsers[usuarios.length] = usuario;
        return newUsers;
    }

    public static Viagem[] arrayFillViagem(Viagem[] viagens, Viagem viagem, Usuario user) {
        Viagem[] newViagem = new Viagem[viagens.length + 1];
        for (Viagem v : viagens) {
            if (v.getDestino().equalsIgnoreCase(viagem.getDestino())
                    && v.getUsuario().getNome().equalsIgnoreCase(user.getNome())) {

                if (v.getDataInicio().equalsIgnoreCase(viagem.getDataInicio())) {
                    System.out.println("Você já tem uma viagem para esse destino nessa data.");
                    return viagens;
                }
            }
        }

        for (int i = 0; i < viagens.length; i++) {
            newViagem[i] = viagens[i];
            newViagem[i].setId(i);
        }

        newViagem[viagens.length] = viagem;
        return newViagem;

    }


    public static Atividade[] arrayFillAtividades(Atividade[] atividades, Atividade atividade) {
        Atividade[] newAtividade = new Atividade[atividades.length + 1];

        for (int i = 0; i < atividades.length; i++) {
            String desc = atividades[i].getDescricao().toLowerCase();

            if (desc.equalsIgnoreCase(atividade.getDescricao())) {
                System.out.println("A atividade já existe");
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

    public static void consultarItinerario(Atividade[] atividades, Viagem[] viagens, Acomodacao[] acomodacoes) {
        Viagem viagemSelecionada = ViagemService.escolhaDeViagem();
        if (viagemSelecionada == null) {
            System.out.println("Nenhuma viagem selecionada.");
            return;
        }

        String destino = viagemSelecionada.getDestino();
        String acomodacaoDestino = null;


        for (Acomodacao acomodacao : acomodacoes) {
            if (acomodacao.getViagem().equals(viagemSelecionada)) {
                acomodacaoDestino = acomodacao.getName();
                break;
            }
        }

        boolean encontrouAtividades = false;
        System.out.println("Atividades da viagem para " + destino + ":");
        for (Atividade atividade : atividades) {
            if (atividade.getViagem().equals(viagemSelecionada)) {
                System.out.println("- " + atividade.getDescricao() +
                        " (Acomodação: " + (acomodacaoDestino != null ? acomodacaoDestino : "Nenhuma") + ")");
                encontrouAtividades = true;
            }
        }

        if (!encontrouAtividades) {
            System.out.println("Nenhuma atividade encontrada para essa viagem.");
        }
    }
    public static void gerarAgenda(Atividade[] todasAtividades) {

        Viagem v = ViagemService.escolhaDeViagem();

        if (v != null){
            int duracao = v.calcDuracao();

            System.out.println(duracao);

            Atividade[] atividades = filtrarAtividadesPorViagem(todasAtividades, v);

            System.out.println("--- Agenda da Viagem ---");

            if (duracao == 0){
                System.out.print("Dia " + duracao + 1 + ": ");
                System.out.println(atividades[0].getDescricao());
                return;
            }

            for (int dia = 1; dia <= duracao; dia++) {
                System.out.print("Dia " + dia + ": ");
                int idx = dia - 1;
                if (idx < atividades.length) {
                    System.out.println(atividades[idx].getDescricao());
                } else {
                    System.out.println("Sem atividades");
                }
            }
        }else{
            System.out.println("v ta nulo");
        }
    }

    public static Atividade[] filtrarAtividadesPorViagem(Atividade[] todasAtividades, Viagem v) {
        int count = 0;
        for (Atividade a : todasAtividades) {
            if (a.getViagem().equals(v)) count++;
        }
        Atividade[] result = new Atividade[count];
        int idx = 0;
        for (Atividade a : todasAtividades) {
            if (a.getViagem().equals(v)) {
                result[idx++] = a;
            }
        }
        return result;
    }

    public static void analisarOrcamento(Usuario[] usuarios, Viagem[] viagens, Atividade[] atividades, Acomodacao[] acomodacoes) {
        System.out.println("=== Total gasto por usuário ===");

        for (Usuario u : usuarios) {
            double total = 0;

            for (Viagem v : viagens) {
                if (v.getUsuario().equals(u)) {
                    Orcamento.setTotalGasto(0);
                    total += Orcamento.getTotalGasto(atividades, acomodacoes, v);
                }
            }

            System.out.printf("%s: R$ %.2f%n", u.getNome(), total);
        }

        double soma = 0;
        for (Viagem v : viagens) {
            Orcamento.setTotalGasto(0);
            soma += Orcamento.getTotalGasto(atividades, acomodacoes, v);
        }

        double media = viagens.length > 0 ? soma / viagens.length : 0;
        System.out.printf("%nMédia de gastos por viagem: R$ %.2f%n", media);

        System.out.println("\n=== Dia mais caro por viagem ===");
        for (Viagem v : viagens) {
            int duracao = v.calcDuracao() + 1;
            double[] gastosPorDia = new double[duracao];

            for (Acomodacao a : acomodacoes) {
                if (a.getViagem().equals(v)) {
                    for (int i = 0; i < Math.min(a.getDiasReservados(), duracao); i++) {
                        gastosPorDia[i] += a.getCustoDiario();
                    }
                }
            }

            int atvIndex = 0;
            for (Atividade a : atividades) {
                if (a.getViagem().equals(v)) {
                    if (atvIndex < duracao) {
                        gastosPorDia[atvIndex++] += a.getCustoEstimado();
                    }
                }
            }

            double maxGasto = 0;
            int diaMaisCaro = 1;
            for (int i = 0; i < duracao; i++) {
                if (gastosPorDia[i] > maxGasto) {
                    maxGasto = gastosPorDia[i];
                    diaMaisCaro = i + 1;
                }
            }

            System.out.printf("Viagem para %s: Dia %d é o mais caro com R$ %.2f%n", v.getDestino(), diaMaisCaro, maxGasto);
        }

        Usuario topUser = null;
        double maxGasto = 0;
        for (Usuario u : usuarios) {
            double total = 0;

            for (Viagem v : viagens) {
                if (v.getUsuario().equals(u)) {
                    Orcamento.setTotalGasto(0);
                    total += Orcamento.getTotalGasto(atividades, acomodacoes, v);
                }
            }

            if (total > maxGasto) {
                maxGasto = total;
                topUser = u;
            }
        }

        if (topUser != null) {
            System.out.printf("%nUsuário que mais gastou: %s com R$ %.2f%n", topUser.getNome(), maxGasto);
        }
    }



    public static void gerarViagemDosSonhos(Usuario u, Viagem[] todasViagens, Atividade[] todasAtividades, Acomodacao[] todasAcomodacoes) {

        String[] destinos = new String[todasViagens.length];
        int[] freq = new int[todasViagens.length];
        int uniqueCount = 0;

        for (Viagem v : todasViagens) {
            if (v.getUsuario().getNome().equalsIgnoreCase(u.getNome())) {
                String dest = v.getDestino();
                int idx = indexOf(destinos, dest, uniqueCount);
                if (idx == -1) {
                    destinos[uniqueCount] = dest;
                    freq[uniqueCount] = 1;
                    uniqueCount++;
                } else {
                    freq[idx]++;
                }
            }
        }

        if (uniqueCount == 0) {
            System.out.println("Nenhuma viagem anterior para gerar sugestão.");
            return;
        }

        int maxFreq = freq[0];
        int maxIdx = 0;
        for (int i = 1; i < uniqueCount; i++) {
            if (freq[i] > maxFreq) {
                maxFreq = freq[i];
                maxIdx = i;
            }
        }
        String destinoSonho = destinos[maxIdx];

        Atividade[] atividadesDoDestino = filtrarAtividadesPorDestino(todasAtividades, destinoSonho);

        Acomodacao acomodacaoSonho = null;
        for (Acomodacao a : todasAcomodacoes) {
            if (a.getViagem().getDestino().equalsIgnoreCase(destinoSonho)) {
                acomodacaoSonho = a;
                break;
            }
        }

        System.out.println("Destino: " + destinoSonho);

        System.out.print("Atividades: ");
        if (atividadesDoDestino.length == 0) {
            System.out.println("Nenhuma atividade registrada");
        } else {
            for (int i = 0; i < atividadesDoDestino.length; i++) {
                System.out.print(atividadesDoDestino[i].getDescricao());
                if (i < atividadesDoDestino.length -1) System.out.print(", ");
            }
            System.out.println();
        }

        if (acomodacaoSonho != null) {
            System.out.printf("Hospedagem: %s, %d noites%n", acomodacaoSonho.getName(), acomodacaoSonho.getDiasReservados());
        } else {
            System.out.println("Hospedagem: Nenhuma registrada");
        }
    }

    private static int indexOf(String[] array, String value, int length) {
        for (int i = 0; i < length; i++) {
            if (array[i].equalsIgnoreCase(value)) return i;
        }
        return -1;
    }

    private static Atividade[] filtrarAtividadesPorDestino(Atividade[] todasAtividades, String destino) {
        int count = 0;
        for (Atividade a : todasAtividades) {
            if (a.getViagem().getDestino().equalsIgnoreCase(destino)) count++;
        }
        Atividade[] result = new Atividade[count];
        int idx = 0;
        for (Atividade a : todasAtividades) {
            if (a.getViagem().getDestino().equalsIgnoreCase(destino)) {
                result[idx++] = a;
            }
        }
        return result;
    }
}
