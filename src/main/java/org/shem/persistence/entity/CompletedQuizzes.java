package org.shem.persistence.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class CompletedQuizzes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int id;

    @JsonProperty("id")
    private int quizId;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long userId;

    private LocalDateTime completedAt;

    public CompletedQuizzes() {
    }

    public CompletedQuizzes(int quizId, Long userId, LocalDateTime completedAt) {
        this.quizId = quizId;
        this.userId = userId;
        this.completedAt = completedAt;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }
}
