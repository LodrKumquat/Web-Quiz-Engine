package engine.controller;

import engine.dto.QuizUserRegistrationRequest;
import engine.dto.QuizDTO;
import engine.dto.Answer;
import engine.dto.Result;

import engine.service.QuizService;
import engine.service.QuizUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
public class QuizController {

    private final QuizService quizService;
    private final QuizUserService quizUserService;

    @Autowired
    public QuizController(QuizService quizService, QuizUserService quizUserService) {
        this.quizService = quizService;
        this.quizUserService = quizUserService;
    }

    @PostMapping("/api/quizzes")
    public QuizDTO addQuiz(@RequestBody @Valid QuizDTO quiz,
                           @AuthenticationPrincipal UserDetails author) {
        return quizService.addQuiz(quiz, author);
    }

    @GetMapping("/api/quizzes/{id}")
    public QuizDTO getQuizById(@PathVariable("id") int id) {
        return quizService.getQuiz(id);
    }

    @GetMapping("/api/quizzes")
    public Iterable<QuizDTO> getAllQuiz() {
        return quizService.getAllQuizzes();
    }

    @PostMapping("/api/quizzes/{id}/solve")
    public Result solveQuiz(@PathVariable("id") int id, @RequestBody Answer answer) {
        return quizService.checkAnswer(id, answer.getAnswer())
                ? Result.CORRECT_RESULT : Result.WRONG_RESULT;
    }

    @PostMapping("/api/register")
    public void registerUser(@Valid @RequestBody QuizUserRegistrationRequest registrationRequest) {
        quizUserService.registerUser(registrationRequest);
    }
}
