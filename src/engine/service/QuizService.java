package engine.service;

import engine.exception.QuizNotFoundException;
import engine.model.Quiz;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class QuizService {

    private final ConcurrentHashMap<Integer, Quiz> quizzes = new ConcurrentHashMap<>();
    private static int idCounter = 0;

    public Quiz addQuiz(Quiz quiz) {
        quiz.setId(idCounter++);
        quizzes.put(quiz.getId(), quiz);
        return quiz;
    }

    public Quiz getQuiz(int id) {
        Optional<Quiz> quizOptional = Optional.ofNullable(quizzes.get(id));
        if (quizOptional.isPresent())
            return quizOptional.get();
        throw new QuizNotFoundException();
    }

    public boolean checkAnswer(int id, List<Integer> answer) {
        List<Integer> actualAnswers = this.getQuiz(id).getAnswer();
        if (actualAnswers == null) {
            return answer == null || answer.isEmpty();
        } else {
            return actualAnswers.equals(answer);
        }
    }

    public Collection<Quiz> getAllQuizzes() {
        return quizzes.values();
    }
}
