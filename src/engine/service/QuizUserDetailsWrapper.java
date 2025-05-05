package engine.service;

import engine.persistence.entity.QuizUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class QuizUserDetailsWrapper implements UserDetails {

    private QuizUser quizUser;

    public QuizUserDetailsWrapper(QuizUser quizUser) {
        this.quizUser = quizUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return quizUser.getPassword();
    }

    @Override
    public String getUsername() {
        return quizUser.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public QuizUser getQuizUser() {
        return quizUser;
    }
}
