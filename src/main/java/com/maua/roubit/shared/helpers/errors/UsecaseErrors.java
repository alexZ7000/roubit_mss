package com.maua.roubit.shared.helpers.errors;

public class UsecaseErrors {
    public static class NoItemsFound extends BaseError {
        public NoItemsFound(String message) {
            super("No items found for " + message);
        }
    }

    public static class DuplicatedItem extends BaseError {
        public DuplicatedItem(String message) {
            super("The item already exists for this " + message);
        }
    }

    public static class ForbiddenAction extends BaseError {
        public ForbiddenAction(String message) {
            super("The action is forbidden for this " + message);
        }
    }
}
