package com.alcaria.eduardo.main;

import com.alcaria.eduardo.domain.*;
import com.alcaria.eduardo.services.*;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

import static com.alcaria.eduardo.services.AcomodacaoService.acomodacoesCopia;
import static com.alcaria.eduardo.services.AtividadeService.atividadesCopia;
import static com.alcaria.eduardo.services.InputHandler.*;
import static com.alcaria.eduardo.services.GeralServices.*;
import static com.alcaria.eduardo.services.UserServices.buscarUsuarioPorNome;
import static com.alcaria.eduardo.services.UserServices.usuariosCopia;
import static com.alcaria.eduardo.services.ViagemService.*;

public class Interface {
    public static Scanner scanner = new Scanner(System.in);

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
                    8. Consultar Orçamento (Aprofundado)
                    9. Gerar Agenda
                    10. Agendar viagem de costume?
                    11. Sair do Programa
                    """);

            int op = lerInteiro("Escolha uma opção: ");
            switch (op) {
                case 1:
                    addUserTela();
                    break;
                case 2:
                    addViagemTela();
                    break;
                case 3:
                    addAtividadeTela();
                    break;
                case 4:
                    addAcomodacaoTela();
                    break;
                case 5:
                    consultarItinerario(atividadesCopia, viagensCopia, acomodacoesCopia);
                    pressioneParaContinuar();
                    break;
                case 6:
                    orcamentoTela();
                    break;
                case 7:
                    mostraViagemDoUserTela();
                    break;
                case 8:
                    analisarOrcamentoTela();
                    break;
                case 9:
                    gerarAgendaTela();
                    break;
                case 10:
                    gerarViagemDosSonhosTela();
                    break;
                case 11:
                    System.out.println("Encerrando o programa.");
                    return;
                default:
                    System.out.println("Opção inválida.\n");
                    pressioneParaContinuar();
            }
        }
    }

    public static void addUserTela() {
        System.out.println("=================== CADASTRO ===================");
        while (true) {
            System.out.print("Nome: ");
            String name = scanner.nextLine();

            String email = emailValidator();

            UserServices.registrarUser(name, email);

            boolean simOuNao = simOuNao("\nCadastrar outro?");
            if (!simOuNao) {
                break;
            }

        }


    }

    public static void orcamentoTela() {
        System.out.println("=================== Orçamento ========================");
        Viagem viagemEscolhida = escolhaDeViagem();
        if (viagemEscolhida == null) {
            return;
        }

        System.out.printf("Você ira gastar R$ %.2f na viagem para %s%n",
                Orcamento.getTotalGasto(atividadesCopia, acomodacoesCopia, viagemEscolhida),
                viagemEscolhida.getDestino());
        pressioneParaContinuar();
    }

    public static void addAtividadeTela() {
        System.out.println("=================== adicionar atividade ===================");
        String data = "";
        Viagem viagemEncontrada = escolhaDeViagem();

        System.out.print("Descrição da atividade: ");
        String desc = scanner.nextLine();

        System.out.print("Custo estimado: ");
        double custoEstimado = scanner.nextDouble();
        scanner.nextLine();

        if (viagemEncontrada.calcDuracao() != 0){
            System.out.print("Data da atividade (dd-MM-yyyy): ");
            data = scanner.nextLine();
        }else{
            data = viagemEncontrada.getDataInicio();
        }

        AtividadeService.addAtividade(desc, custoEstimado, viagemEncontrada, data);
        pressioneParaContinuar();
    }


    public static void addAcomodacaoTela() {
        System.out.println("=================== Adicionar Acomodação ===================");
        int dias;
        Viagem viagemEncontrada = escolhaDeViagem();

        System.out.print("Nome da acomodação: ");

        String nome = scanner.nextLine();

        System.out.print("Preço por dia: ");

        double preco = scanner.nextDouble();
        scanner.nextLine();

        if (viagemEncontrada.calcDuracao() != 0){
           dias = lerInteiro("Número de dias: ");
        }else{
            dias = 1;
        }

        AcomodacaoService.adicionarAcomodacao(nome, preco, dias, viagemEncontrada);

    }

    public static void addViagemTela() {

        System.out.println("Onde voce deseja ir: ");

        String destino = scanner.nextLine();

        System.out.println("Qual é o seu nome de usuario");
        String nomeUser = scanner.nextLine();

        System.out.println("Digite a data de inico (dd-MM-yyyy): ");

        String dataInicio = scanner.nextLine();

        System.out.println("Digite a data de fim (dd-MM-yyyy): ");

        String dataFim = scanner.nextLine();

        registrarViagem(destino, nomeUser, dataInicio, dataFim);

        pressioneParaContinuar();

    }

    public static void mostraViagemDoUserTela() {
        System.out.println("=================== Procura ===================");
        System.out.println("Qual é o seu user name? ");
        String nome = scanner.nextLine();
        consultarViagensPorUsuario(nome);
    }
    public static void analisarOrcamentoTela() {
        System.out.println("=================== ANÁLISE DE ORÇAMENTO ===================");

        analisarOrcamento(usuariosCopia, viagensCopia, atividadesCopia, acomodacoesCopia);

        pressioneParaContinuar();
    }

    public static void gerarAgendaTela(){
        System.out.println("=================== Gerar Agenda ===================");
        gerarAgenda(atividadesCopia);

        pressioneParaContinuar();
    }

    public static void gerarViagemDosSonhosTela(){
        System.out.println("=================== Gerar Viagem Dos Sonhos ===================");
        System.out.println("Qual é o seu user name? ");
        String userName = scanner.nextLine();
        Usuario user = buscarUsuarioPorNome(userName);
        gerarViagemDosSonhos(user, viagensCopia, atividadesCopia, acomodacoesCopia);
        pressioneParaContinuar();
    }

    public static void cleanTerminal(){
        for (int i = 0; i < 100; i++) {
            System.out.println();
        }
    }

    public static void banner() {
        cleanTerminal();
        System.out.println("""
                
                  __                            .__           .__                                    \s
                _/  |_____________ ___  __ ____ |  |   ______ |  | _____    ____   ____   ___________\s
                \\   __\\_  __ \\__  \\\\  \\/ // __ \\|  |   \\____ \\|  | \\__  \\  /    \\ /    \\_/ __ \\_  __ \\
                 |  |  |  | \\// __ \\\\   /\\  ___/|  |__ |  |_> >  |__/ __ \\|   |  \\   |  \\  ___/|  | \\/
                 |__|  |__|  (____  /\\_/  \\___  >____/ |   __/|____(____  /___|  /___|  /\\___  >__|  \s
                                  \\/          \\/       |__|             \\/     \\/     \\/     \\/      \s
                
                By: Eduardo Alcaria, Pietro Tessele
                """);
        sleep(1);
    }

    public static void sleep(int segsToSleep){
        LocalTime now = LocalTime.now();


        LocalTime sumAll = ChronoUnit.SECONDS.addTo(now, segsToSleep);

        while (true){
            if (LocalTime.now().isAfter(sumAll)){
                break;
            }
        }



    }
}
