package engine.controller;

import engine.exception.DeletionRequestUnauthorizedException;
import engine.exception.QuizNotFoundException;
import engine.exception.UserEmailAlreadyRegisteredException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(QuizNotFoundException.class)
    public ResponseEntity<String> handleQuizNotFoundException(
            QuizNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserEmailAlreadyRegisteredException.class)
    public ResponseEntity<String> handleUserEmailAlreadyRegisteredException(
            UserEmailAlreadyRegisteredException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DeletionRequestUnauthorizedException.class)
    public ResponseEntity<String> handleDeletionRequestUnauthorizedException(
            DeletionRequestUnauthorizedException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
    }
}