package br.com.profitness.exceptions;


import jakarta.annotation.Nullable;

public class ClientServicesExceptions extends IllegalArgumentException {

     public ClientServicesExceptions(String mensagem) {
        super(mensagem);
    }


}
