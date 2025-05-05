package engine.persistence.repository;

import engine.persistence.entity.QuizUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<QuizUser, Integer> {

    Optional<QuizUser> findQuizUserByUsername(String username);
}
