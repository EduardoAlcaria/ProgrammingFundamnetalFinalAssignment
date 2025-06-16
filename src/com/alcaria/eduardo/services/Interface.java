package com.alcaria.eduardo.services;

import com.alcaria.eduardo.domain.*;

import java.time.LocalTime;
import java.util.Scanner;

public class Interface {
    public static Scanner scanner = new Scanner(System.in);
    public static Usuario[] usuariosCopia = new Usuario[0];
    public static Viagem[] viagensCopia = new Viagem[0];
    public static Atividade[] atividadesCopia = new Atividade[0];
    public static Acomodacao[] acomodacoesCopia = new Acomodacao[0];

    public static void menu() {
        while (true) {
            banner();
            System.out.println("==================== INÍCIO =======================");
            System.out.println("""
                    1. Cadastro de Usuário
                    2. Cadastro de Viagem
                    3. Adicionar Atividade
                    4. Adicionar Acomodação
                    5. Consultar Itinerário
                    6. Calcular Orçamento Total
                    7. Consultar Viagens por Usuário
                    8. Funcionalidade Inovadora
                    9. Sair do programa
                    """);

            int op = lerInteiro("Escolha uma opção: ");
            switch (op) {
                case 1:
                    registerScreen();
                    break;
                case 2:
                    registrarViagem();
                    break;
                case 3:
                    addAtividade();

                    break;
                case 4:
                    adicionarAcomodacao();
                    break;
                case 5:
                    Services.consultarItinerario(atividadesCopia, viagensCopia, acomodacoesCopia, scanner);
                    pressioneParaContinuar();
                    break;
                case 6:
                    orcamentoTela();
                    break;
                case 7:
                    consultarViagensPorUsuario();
                    break;
                case 8:
                    System.out.println("Função ainda não implementada.\n");
                    pressioneParaContinuar();
                    break;
                case 9:
                    System.out.println("Encerrando o programa.");
                    return;
                default:
                    System.out.println("Opção inválida.\n");
                    pressioneParaContinuar();
            }
        }
    }

    private static void registerScreen() {
        int idCounter = 0;
        Usuario[] usuarios = new Usuario[0];
        while (true) {
            System.out.println("=================== CADASTRO ===================");
            System.out.print("Nome: ");
            String name = scanner.nextLine();

            String email = emailValidator();

            Usuario novoUsuario = new Usuario(idCounter++, name, email);
            usuarios = Services.cadastroDeUser(usuarios, novoUsuario);
            usuariosCopia = usuarios;


            System.out.println("Usuários cadastrados:");
            for (Usuario u : usuarios) {
                System.out.println("- " + u.getName() + " (" + u.getEmail() + ")");
            }

            boolean simOuNao = simOuNao("\nCadastrar outro?");
            if (!simOuNao) {
                break;
            }
        }
        pressioneParaContinuar();
    }

    public static void registrarViagem() {
        Viagem[] viagensLocal;
        String name;


        System.out.println("=================== trip register ========================");
        System.out.println("Onde voce deseja ir: ");
        String trip = scanner.nextLine();

        System.out.println("Qual é o seu nome de usuario");
        name = scanner.nextLine();

        Usuario user = buscarUsuarioPorNome(name);

        if (!user.equals(null)){
            Viagem viagem;
            String dataInicio, dataFim;
            while (true){
                System.out.println("Digite a data de inico (dd-MM-yyyy): ");

                dataInicio = scanner.nextLine();

                System.out.println("Digite a data de fim (dd-MM-yyyy): ");

                dataFim = scanner.nextLine();

                if (Viagem.formatter(dataInicio, dataFim)) {
                    break;
                } else {
                    System.out.println("Data inválida. Use o formato dd-MM-yyyy e verifique a ordem.");
                }

            }

            viagem = new Viagem(trip, user, dataInicio , dataFim);
            viagensLocal = Services.arrayFillViagem(viagensCopia, viagem, user);
            viagensCopia = viagensLocal;

            Services.cadastroViagem(user,viagem);


        }else{
            System.out.println("O usuário " + name + " não existe");
            int op = lerInteiro("Gostaria de tentar de novo[1], ou criar um usuário[2]? :> ");
            scanner.nextLine();
            if (op == 1){
                registrarViagem();
            }
            if (op == 2) {
                registerScreen();
            }
        }
        pressioneParaContinuar();
    }

    private static void addAtividade(){
        Atividade[] atividadesLocal;
        System.out.println("=================== add atividade ===================");
        Viagem viagemEncontrada = escolhaDeViagem();

        if (viagemEncontrada == null){
            return;
        }

        System.out.println("Descricao da atividade: ");
        String desc = scanner.nextLine();

        System.out.println("custo estimado: ");
        double custoEstimado = scanner.nextDouble();
        scanner.nextLine();

        Atividade atividade = new Atividade(atividadesCopia.length, desc, custoEstimado, viagemEncontrada);
        atividadesLocal = Services.arrayFillAtividades(atividadesCopia, atividade);
        atividadesCopia = atividadesLocal;


        pressioneParaContinuar();
    }


    public static void consultarViagensPorUsuario() {
        System.out.print("Informe o nome do usuário para listar as viagens: ");
        String nomeUsuario = scanner.nextLine();

        boolean achou = false;
        System.out.println("Viagens do usuário " + nomeUsuario + ": ");
        for (Viagem v : viagensCopia) {
            if (v.getUsuario().getName().equalsIgnoreCase(nomeUsuario)) {
                System.out.println("- " + v.getDestino() + " (" + v.getDataInicio() + " a " + v.getDataFim() + ")");
                achou = true;
            }
        }

        if (!achou) {
            System.out.println("Nenhuma viagem encontrada para este usuário.");
        }
        pressioneParaContinuar();
    }


    public static void adicionarAcomodacao() {
        System.out.println("=================== Adicionar Acomodação ===================");
        Viagem viagemEncontrada = escolhaDeViagem();

        if (viagemEncontrada == null){
            return;
        }

        System.out.print("Nome da acomodação: ");
        String nome = scanner.nextLine();
        System.out.print("Preço por dia: ");
        double preco = scanner.nextDouble();
        int dias = lerInteiro("Número de dias: ");

        Acomodacao acomodacao = new Acomodacao(acomodacoesCopia.length, nome, preco, dias, viagemEncontrada);
        acomodacoesCopia = Services.arrayFillAcomodacoes(acomodacoesCopia, acomodacao);


        System.out.println("Acomodação adicionada com sucesso!");
        pressioneParaContinuar();
    }
    public static void orcamentoTela(){
        System.out.println("=================== trip register ========================");
        Viagem viagemEscolhida = escolhaDeViagem();
        if (viagemEscolhida == null){
            return;
        }

        System.out.printf("Você gastou R$ %.2f na viagem para %s%n",
                Orcamento.getTotalGasto(atividadesCopia, acomodacoesCopia, viagemEscolhida),
                viagemEscolhida.getDestino());
    }

    public static void banner() {
        for (int i = 0; i < 100; i++) {
            System.out.println();
        }
        System.out.println("""
                
                  __                            .__           .__                                    \s
                _/  |_____________ ___  __ ____ |  |   ______ |  | _____    ____   ____   ___________\s
                \\   __\\_  __ \\__  \\\\  \\/ // __ \\|  |   \\____ \\|  | \\__  \\  /    \\ /    \\_/ __ \\_  __ \\
                 |  |  |  | \\// __ \\\\   /\\  ___/|  |__ |  |_> >  |__/ __ \\|   |  \\   |  \\  ___/|  | \\/
                 |__|  |__|  (____  /\\_/  \\___  >____/ |   __/|____(____  /___|  /___|  /\\___  >__|  \s
                                  \\/          \\/       |__|             \\/     \\/     \\/     \\/      \s
                
                By: Eduardo Alcaria, Pietro
                """);
        sleep(1);

    }

    public static Viagem escolhaDeViagem(){
        Viagem viagemEncontrada = null;

        if (viagensCopia == null || viagensCopia.length == 0) {

            boolean simOuNao = simOuNao("Ops.. parece que nao temos nenhuma viagem registrada, gostaria de registrar uma?");

            if (simOuNao) {
                registrarViagem();
            }else{
                return null;
            }

        } else {
            while (true) {
                System.out.println("Escolha uma das viagens registradas");
                for (int i = 0; i < viagensCopia.length; i++) {
                    viagensCopia[i].setId(i);
                    System.out.println((i + 1) + " * " + viagensCopia[i].getDestino());

                }
                int numeroViagem = lerInteiro(":> ");

                for (Viagem v : viagensCopia) {
                    if (v.getId() == numeroViagem) {
                        viagemEncontrada = v;
                        break;
                    }
                }

                if (viagemEncontrada == null) {
                    System.out.println("Viagem nao encontrada, tente novamente.");
                } else {
                    break;
                }
            }
        }
        pressioneParaContinuar();
        return viagemEncontrada;
    }

    public static Usuario buscarUsuarioPorNome(String nome) {
        for (Usuario user : usuariosCopia) {
            if (user.getName().equalsIgnoreCase(nome)) {
                return user;
            }
        }
        return null;
    }

    public static void sleep(int seg){
        LocalTime time = LocalTime.now();
        int localSegs = time.getSecond();
        int target = localSegs + seg;

        while (true){
            LocalTime timeMethod = LocalTime.now();
            int segsMethod = timeMethod.getSecond();
            if (segsMethod == target) {
                break;
            }
        }
    }
    public static String emailValidator(){
        String email;
        while (true){
            System.out.print("Email: ");
            email = scanner.nextLine();

            if (!email.contains("@") || !email.contains(".com")){
                System.out.println("this is not a valid email");
            }else{
                break;
            }
        }
        return email;
    }
    public static void pressioneParaContinuar(){
        System.out.println("aperte qualquer tecla para continuar: ");
        scanner.nextLine();
        sleep(1);
    }
    public static boolean simOuNao(String msg){
        while (true) {
            System.out.print(msg + " [s/n]: ");
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("s")) {
                return true;
            }
            if (input.equals("n")) {
                return false;
            }
            System.out.println("Entrada inválida.");
        }
    }
    public static int lerInteiro(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.print("Entrada inválida. Tente novamente: ");
            scanner.next();
        }
        int valor = scanner.nextInt();
        scanner.nextLine();
        return valor;
    }

}
