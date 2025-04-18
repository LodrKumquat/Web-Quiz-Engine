package engine;

import engine.model.Quiz;
import engine.model.Result;
import org.springframework.web.bind.annotation.*;

@RestController
public class QuizController {

    @GetMapping("/api/quiz")
    public Quiz getQuiz() {
        return new Quiz();
    }

    @PostMapping("/api/quiz")
    public Result getResult(@RequestParam("answer") int answer) {
        return answer == 2 ? Result.CORRECT_RESULT : Result.WRONG_RESULT;
    }
}
