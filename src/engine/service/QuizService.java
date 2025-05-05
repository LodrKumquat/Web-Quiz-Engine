package engine.service;

import engine.exception.QuizNotFoundException;
import engine.persistence.entity.Quiz;
import engine.dto.QuizDTO;
import engine.persistence.repository.QuizRepository;
import engine.persistence.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    private final QuizRepository quizRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public QuizService(QuizRepository quizRepository, ModelMapper modelMapper) {
        this.quizRepository = quizRepository;
        this.modelMapper = modelMapper;
    }

    public QuizDTO addQuiz(QuizDTO quizDTO, UserDetails author) {
        Quiz quiz = modelMapper.map(quizDTO, Quiz.class);
        quiz.setAuthor(((QuizUserDetailsWrapper) author).getQuizUser());
        quizRepository.save(quiz);
        return modelMapper.map(quiz, QuizDTO.class);
    }

    public QuizDTO getQuiz(int id) {
        Optional<Quiz> quizOptional = quizRepository.findById(id);
        if (quizOptional.isPresent()) {
            return modelMapper.map(quizOptional.get(), QuizDTO.class);
        } else {
            throw new QuizNotFoundException();
        }
    }

    public boolean checkAnswer(int id, List<Integer> answer) {
        return answer.equals(new ArrayList<>(this.getQuiz(id).getAnswer()));

    }

    public Iterable<QuizDTO> getAllQuizzes() {
        List<QuizDTO> quizzes = new ArrayList<>();
        quizRepository.findAll()
                .forEach(quiz -> quizzes.add(modelMapper.map(quiz, QuizDTO.class)));
        return quizzes;
    }
}
