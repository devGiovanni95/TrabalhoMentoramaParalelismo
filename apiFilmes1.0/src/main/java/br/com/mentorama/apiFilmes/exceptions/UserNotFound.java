package br.com.mentorama.apiFilmes.exceptions;

public class UserNotFound extends InterruptedException{

    public UserNotFound(){
        System.out.println("Usuario não encontrado" );
    }

}
