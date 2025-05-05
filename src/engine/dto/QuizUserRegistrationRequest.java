package engine.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record QuizUserRegistrationRequest(
        @Email(regexp = "^\\w+@\\w+\\.[A-Za-z]{2,}$") String email,
        @NotBlank @Size(min = 5) String password) {}
