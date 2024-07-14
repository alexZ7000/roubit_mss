package com.maua.roubit.shared.utils.validators.domain.enums;

public class EnumValidator {

    public static boolean validateEnum(Enum<?>[] enumValues, String value) {
        for (Enum<?> enumValue : enumValues) {
            if (enumValue.name().equals(value)) {
                return true;
            }
        }
        return false;
    }
}

