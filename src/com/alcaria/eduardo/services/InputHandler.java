package com.alcaria.eduardo.services;

import static com.alcaria.eduardo.main.Interface.scanner;

public class InputHandler {
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
    public static void pressioneParaContinuar(){
        System.out.println("aperte qualquer tecla para continuar: ");
        String tecla = scanner.nextLine();
    }

}
