package engine.exception;

public class QuizNotFoundException extends RuntimeException {

    public QuizNotFoundException() {
        super("Quiz not found by provided ID");
    }
}
