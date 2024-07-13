package com.maua.roubit.shared.helpers.errors;

public class BaseError extends Exception {
    private final String message;

    public BaseError(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}

