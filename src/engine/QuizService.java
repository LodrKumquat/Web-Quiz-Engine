package engine;

import engine.model.Quiz;
import org.springframework.stereotype.Service;

import java.util.Collection;
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
        return quizzes.get(id);
    }

    public Collection<Quiz> getAllQuizzes() {
        return quizzes.values();
    }
}
