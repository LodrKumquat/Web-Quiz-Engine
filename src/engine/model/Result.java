package engine.model;

public class Result {

    private boolean success;
    private String feedback;

    private static final String CORRECT_FEEDBACK = "Congratulations, you're right!";
    private static final String WRONG_FEEDBACK = "Wrong answer! Please, try again.";

    public static final Result CORRECT_RESULT = new Result(true, CORRECT_FEEDBACK);
    public static final Result WRONG_RESULT = new Result(false, WRONG_FEEDBACK);

    public Result() {
    }

    private Result(boolean success, String feedback) {
        this.success = success;
        this.feedback = feedback;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getFeedback() {
        return feedback;
    }
}
