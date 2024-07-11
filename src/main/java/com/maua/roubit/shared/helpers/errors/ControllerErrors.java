package com.maua.roubit.shared.helpers.errors;


public class ControllerErrors {
    public static class MissingParameters extends BaseError {
        public MissingParameters(String fieldName) {
            super("Field " + fieldName + " is missing");
        }
    }

    public static class WrongTypeParameters extends BaseError {
        public WrongTypeParameters(String fieldName, String fieldTypeExpected, String fieldTypeReceived) {
            super("Field " + fieldName + " isn't in the right type.\n Received: " + fieldTypeReceived + ".\n Expected: " + fieldTypeExpected + ".");
        }
    }
}


