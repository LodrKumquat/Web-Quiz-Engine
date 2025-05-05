package engine.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record QuizUserRegistrationRequest(@Email String email, @Size(min = 5) String password) {}
