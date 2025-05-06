package engine.controller;

import engine.dto.Answer;
import engine.dto.Result;
import engine.dto.QuizDTO;
import engine.dto.QuizUserRegistrationRequest;
import engine.service.QuizService;
import engine.service.QuizUserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
public class QuizController {

    private final QuizService quizService;
    private final QuizUserService quizUserService;

    @Autowired
    public QuizController(QuizService quizService, QuizUserService quizUserService) {
        this.quizService = quizService;
        this.quizUserService = quizUserService;
    }

    @PostMapping("/api/register")
    public void registerUser(@Valid @RequestBody QuizUserRegistrationRequest registrationRequest) {
        quizUserService.registerUser(registrationRequest);
    }

    @PostMapping("/api/quizzes")
    public QuizDTO addQuiz(@RequestBody @Valid QuizDTO quiz,
                           @AuthenticationPrincipal UserDetails author) {
        return quizService.addQuizByAuthor(quiz, author);
    }

    @GetMapping("/api/quizzes/{id}")
    public QuizDTO getQuizById(@PathVariable("id") int id) {
        return quizService.getQuizById(id);
    }

    @GetMapping("/api/quizzes")
    public Iterable<QuizDTO> getAllQuiz(@RequestParam @Min(0) int page) {
        return quizService.getAllQuizzes(page);
    }

    @PostMapping("/api/quizzes/{id}/solve")
    public Result solveQuiz(@PathVariable("id") int id, @RequestBody Answer answer) {
        return quizService.checkAnswer(id, answer.getAnswer())
                ? Result.CORRECT_RESULT : Result.WRONG_RESULT;
    }

    @DeleteMapping("/api/quizzes/{id}")
    public ResponseEntity<String> deleteQuiz(@PathVariable("id") int id,
                                             @AuthenticationPrincipal UserDetails user) {
        quizService.deleteByIdAndUser(id, user);
        return new ResponseEntity<>("Quiz deleted", HttpStatus.NO_CONTENT);
    }
}
