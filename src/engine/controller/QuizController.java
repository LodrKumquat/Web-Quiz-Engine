package engine.controller;

import engine.exception.QuizNotFoundException;
import engine.dto.*;
import engine.persistence.entity.QuizUser;
import engine.persistence.repository.UserRepository;
import engine.service.QuizService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class QuizController {

    private final QuizService quizService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public QuizController(QuizService quizService, UserRepository userRepository,
                          PasswordEncoder passwordEncoder) {
        this.quizService = quizService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/api/quizzes")
    public QuizDTO addQuiz(@RequestBody @Valid QuizDTO quiz) {
        return quizService.addQuiz(quiz);
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
    public void registerUser(@Valid @RequestBody RegistrationRequest registrationRequest) {
        QuizUser newUser = new QuizUser();
        newUser.setUsername(registrationRequest.email);
        newUser.setPassword(passwordEncoder.encode(registrationRequest.password));
        userRepository.save(newUser);
    }

    public record RegistrationRequest(@Email String email, @Size(min = 5) String password) {}

    @ExceptionHandler(QuizNotFoundException.class)
    public ResponseEntity<String> handleQuizNotFoundException(
            QuizNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
