package com.alcaria.eduardo.services;

import com.alcaria.eduardo.domain.Usuario;

public class UserServices {
    public static Usuario[] usuariosCopia = new Usuario[0];


    public static void registrarUser(String name, String email) {

        int id = usuariosCopia.length;

        Usuario novoUsuario = new Usuario(id, name, email);

        usuariosCopia = GeralServices.arrayFillUser(usuariosCopia, novoUsuario);

        System.out.println("Usuários cadastrados:");
        for (Usuario u : usuariosCopia) {
            System.out.println("- " + u.getNome() + " (" + u.getEmail() + ")");
        }
    }


    public static Usuario buscarUsuarioPorNome(String nome) {
        for (Usuario user : usuariosCopia) {
            if (user.getNome().equalsIgnoreCase(nome)) {
                return user;
            }
        }
        return null;
    }
}