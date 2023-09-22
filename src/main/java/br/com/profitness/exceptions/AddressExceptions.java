package br.com.profitness.exceptions;

import jakarta.annotation.Nullable;

public class AddressExceptions extends IllegalArgumentException{

    public AddressExceptions(@Nullable String mensagem) {
        super(mensagem);
    }


}
