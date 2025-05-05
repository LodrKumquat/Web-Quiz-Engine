package engine.exception;

public class DeletionRequestUnauthorizedException extends RuntimeException {
    public DeletionRequestUnauthorizedException() {
        super("Deletion request unauthorized");
    }
}
