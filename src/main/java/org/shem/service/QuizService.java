package org.shem.service;

import org.shem.exception.DeletionRequestUnauthorizedException;
import org.shem.exception.QuizNotFoundException;
import org.shem.persistence.entity.Quiz;
import org.shem.dto.QuizDTO;
import org.shem.persistence.entity.QuizUser;
import org.shem.persistence.entity.wrapper.QuizUserDetailsWrapper;
import org.shem.persistence.repository.QuizRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public QuizDTO addQuizByAuthor(QuizDTO quizDTO, UserDetails author) {
        Quiz quiz = modelMapper.map(quizDTO, Quiz.class);
        quiz.setAuthor(((QuizUserDetailsWrapper) author).getQuizUser());
        quizRepository.save(quiz);
        return modelMapper.map(quiz, QuizDTO.class);
    }

    public QuizDTO getQuizById(int id) {
        return modelMapper.map(fetchQuiz(id), QuizDTO.class);
    }

    public boolean checkAnswer(int id, List<Integer> answer) {
        return answer.equals(new ArrayList<>(this.fetchQuiz(id).getAnswer()));
    }

    public Page<QuizDTO> getAllQuizzes(int pageNum) {
        return quizRepository.findAll(PageRequest.of(pageNum, 10))
                .map(quiz -> modelMapper.map(quiz, QuizDTO.class));
    }

    @Transactional
    public void deleteByIdAndUser(int id, UserDetails user) {
        Quiz quiz = fetchQuiz(id);
        QuizUser requestAuthor = ((QuizUserDetailsWrapper) user).getQuizUser();
        if (requestAuthor.getId().equals(quiz.getAuthor().getId())) {
            quizRepository.delete(quiz);
        } else {
            throw new DeletionRequestUnauthorizedException();
        }
    }

    private Quiz fetchQuiz(int id) {
        Optional<Quiz> quizOptional = quizRepository.findById(id);
        if (quizOptional.isPresent()) {
            return quizOptional.get();
        } else {
            throw new QuizNotFoundException();
        }
    }
}
