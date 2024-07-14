package com.maua.roubit.shared.utils.validators.errors;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ValidationUtil {
    public static <T> Map<String, String> validateAndGetViolations(Validator validator, T entity) {
        final Set<ConstraintViolation<T>> violations = validator.validate(entity);

        if (violations.isEmpty()) {
            throw new AssertionError("Experado erros de validação, mas não foi encontrado nenhum.");
        }

        final Map<String, String> violationMessages = new HashMap<>();
        for (ConstraintViolation<T> violation : violations) {
            final String propertyPath = violation.getPropertyPath().toString();
            final String message = violation.getMessage();
            violationMessages.put(propertyPath, message);
            System.out.println("Campo: " + propertyPath + ", Erro: " + message);
        }
        return violationMessages;
    }
}
