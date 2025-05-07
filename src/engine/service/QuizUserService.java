package engine.service;

import engine.dto.QuizUserRegistrationRequest;
import engine.exception.UserEmailAlreadyRegisteredException;
import engine.persistence.entity.CompletedQuizzes;
import engine.persistence.entity.QuizUser;
import engine.persistence.entity.wrapper.QuizUserDetailsWrapper;
import engine.persistence.repository.CompletedQuizzesRepository;
import engine.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class QuizUserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final CompletedQuizzesRepository completedQuizzesRepository;

    @Autowired
    public QuizUserService(UserRepository userRepository, PasswordEncoder passwordEncoder,
                           CompletedQuizzesRepository completedQuizzesRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.completedQuizzesRepository = completedQuizzesRepository;
    }

    @Transactional
    public void registerUser(QuizUserRegistrationRequest registrationRequest) {
        if (userRepository.findQuizUserByUsername(registrationRequest.email()).isPresent()) {
            throw new UserEmailAlreadyRegisteredException();
        } else {
            QuizUser newUser = new QuizUser();
            newUser.setUsername(registrationRequest.email());
            newUser.setPassword(passwordEncoder.encode(registrationRequest.password()));
            userRepository.save(newUser);
        }
    }

    @Transactional
    public void recordSuccessfulSolution(int quizId, UserDetails user) {
        Long currentUserId = ((QuizUserDetailsWrapper) user).getQuizUser().getId();
        CompletedQuizzes record = new CompletedQuizzes(quizId, currentUserId, LocalDateTime.now());
        completedQuizzesRepository.save(record);
    }

    public Page<CompletedQuizzes> getCompletedQuizzesByUser(int page, UserDetails user) {
        Long currentUserId = ((QuizUserDetailsWrapper) user).getQuizUser().getId();
        return completedQuizzesRepository.findByUserId(currentUserId,
                PageRequest.of(page, 10));
    }
}
