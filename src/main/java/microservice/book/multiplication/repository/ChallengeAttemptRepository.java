package microservice.book.multiplication.repository;

import microservice.book.multiplication.model.ChallengeAttempt;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ChallengeAttemptRepository extends CrudRepository<ChallengeAttempt, Long> {

    List<ChallengeAttempt> findTop10ByUserAliesOrderByIdDesc(String userAlias);

    @Query("SELECT a FROM ChallengeAttempt a WHERE a.user.alias =?1 ORDER BY a.id DESC")
    List<ChallengeAttempt> lastAttempts(String userAlias);
}
