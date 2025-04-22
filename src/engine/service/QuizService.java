package engine.service;

import engine.exception.QuizNotFoundException;
import engine.model.Quiz;
import engine.persistence.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    private final QuizRepository quizRepository;

    @Autowired
    public QuizService(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    public Quiz addQuiz(Quiz quiz) {
        quizRepository.save(quiz);
        return quiz;
    }

    public Quiz getQuiz(int id) {
        Optional<Quiz> quizOptional = quizRepository.findById(id);
        if (quizOptional.isPresent()) {
            return quizOptional.get();
        } else {
            throw new QuizNotFoundException();
        }
    }

    public boolean checkAnswer(int id, List<Integer> answer) {
        return answer.equals(new ArrayList<>(this.getQuiz(id).getAnswer()));

    }

    public Iterable<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }
}
