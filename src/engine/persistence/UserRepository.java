package engine.persistence;

import engine.model.QuizUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<QuizUser, Integer> {

    Optional<QuizUser> findQuizUserByUsername(String username);
}
