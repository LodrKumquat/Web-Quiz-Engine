package org.shem.exception;

public class UserEmailAlreadyRegisteredException extends RuntimeException {
    public UserEmailAlreadyRegisteredException() {
        super("User with such email already registered!");
    }
}
