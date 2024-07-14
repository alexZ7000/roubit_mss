package com.maua.roubit.shared.helpers.errors;

public class DomainErrors extends BaseError {

    private DomainErrors(final String message) {
        super("Field " + message + " is not valid");
    }

    public static class EntityError extends DomainErrors {
        public EntityError(final String message){
            super(message);
        }
    }

    public static class EntityParameterTypeError extends EntityError {
        public EntityParameterTypeError(final String message) {
            super(message);
        }
    }

    public static class EntityParameterError extends EntityError {
        public EntityParameterError(final String message) {
            super(message);
        }
    }
}
