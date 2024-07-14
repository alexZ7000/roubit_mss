package com.maua.roubit.shared.utils.validators.domain.entities;

import java.util.UUID;

public class UUIDValidator {
    public static boolean validateUUID(final String campo, final String value) {
        try {
            if (value.length() == 36){
                UUID.fromString(value);
                return true;
            }
            else {
                System.out.println("Campo: " + campo + " Erro: UUID Inválido");
                return false;
            }
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}

