package com.maua.roubit.shared.helpers.errors;

public class DomainErrors extends BaseError {
    public DomainErrors(String message) {
        super("Field " + message + " is not valid");
    }
}
