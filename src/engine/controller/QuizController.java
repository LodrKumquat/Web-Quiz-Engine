package engine.controller;

import engine.exception.QuizNotFoundException;
import engine.service.QuizService;
import engine.model.Answer;
import engine.model.Quiz;
import engine.model.Result;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class QuizController {

    private final QuizService quizService;

    @Autowired
    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @PostMapping("/api/quizzes")
    public Quiz addQuiz(@RequestBody @Valid Quiz quiz) {
        return quizService.addQuiz(quiz);
    }

    @GetMapping("/api/quizzes/{id}")
    public Quiz getQuizById(@PathVariable("id") int id) {
        return quizService.getQuiz(id);
    }

    @GetMapping("/api/quizzes")
    public Iterable<Quiz> getAllQuiz() {
        return quizService.getAllQuizzes();
    }

    @PostMapping("/api/quizzes/{id}/solve")
    public Result solveQuiz(@PathVariable("id") int id,
                                            @RequestBody Answer answer) {
        return quizService.checkAnswer(id, answer.getAnswer())
                ? Result.CORRECT_RESULT : Result.WRONG_RESULT;
    }

    @ExceptionHandler(QuizNotFoundException.class)
    public ResponseEntity<String> handleQuizNotFoundException(
            QuizNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
