package org.shem.exception;

public class DeletionRequestUnauthorizedException extends RuntimeException {
    public DeletionRequestUnauthorizedException() {
        super("Deletion request unauthorized");
    }
}
