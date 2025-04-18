package engine;

import engine.model.Quiz;
import engine.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class QuizController {

    private QuizService quizService;

    @Autowired
    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @PostMapping("/api/quizzes")
    public Quiz addQuiz(@RequestBody Quiz quiz) {
        return quizService.addQuiz(quiz);
    }

    @GetMapping("/api/quizzes/{id}")
    public ResponseEntity<Quiz> getQuizById(@PathVariable("id") int id) {
        Quiz quiz = quizService.getQuiz(id);
        return quiz != null ? ResponseEntity.ok(quiz) : ResponseEntity.notFound().build();
    }

    @GetMapping("/api/quizzes")
    public Collection<Quiz> getAllQuiz() {
        return quizService.getAllQuizzes();
    }

    @PostMapping("/api/quizzes/{id}/solve")
    public ResponseEntity<Result> solveQuiz(@PathVariable("id") int id, @RequestParam("answer") int answer) {
        Quiz quiz = quizService.getQuiz(id);
        return quiz != null
                ? ResponseEntity.ok(quizService.getQuiz(id).getAnswer()
                == answer ? Result.CORRECT_RESULT : Result.WRONG_RESULT)
                : ResponseEntity.notFound().build();
    }
}
