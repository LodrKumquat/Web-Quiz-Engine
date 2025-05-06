package engine.service;

import engine.dto.QuizUserRegistrationRequest;
import engine.exception.UserEmailAlreadyRegisteredException;
import engine.persistence.entity.QuizUser;
import engine.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class QuizUserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public QuizUserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
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
}
