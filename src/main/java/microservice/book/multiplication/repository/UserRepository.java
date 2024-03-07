package microservice.book.multiplication.repository;


import microservice.book.multiplication.model.ChallengeUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<ChallengeUser, Long> {

    Optional<ChallengeUser> findByAlias(final String alias);
}
