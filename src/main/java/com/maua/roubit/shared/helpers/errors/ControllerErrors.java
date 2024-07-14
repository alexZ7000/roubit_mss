package com.maua.roubit.shared.helpers.errors;


public class ControllerErrors {
    public static class MissingParameters extends BaseError {
        public MissingParameters(final String fieldName) {
            super("Field " + fieldName + " is missing");
        }
    }

    public static class WrongTypeParameters extends BaseError {
        public WrongTypeParameters(final String fieldName, final String fieldTypeExpected, final String fieldTypeReceived) {
            super("Field " + fieldName + " isn't in the right type.\n Received: " + fieldTypeReceived + ".\n Expected: " + fieldTypeExpected + ".");
        }
    }
}


