package org.shem.service;

import org.shem.persistence.entity.QuizUser;
import org.shem.persistence.entity.wrapper.QuizUserDetailsWrapper;
import org.shem.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class QuizUserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public QuizUserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QuizUser quizUser = userRepository
                .findQuizUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new QuizUserDetailsWrapper(quizUser);
    }
}