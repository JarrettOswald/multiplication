package microservice.book.multiplication.service;


import microservice.book.multiplication.model.ChallengeAttempt;
import microservice.book.multiplication.model.ChallengeAttemptDTO;

import java.util.List;

public interface ChallengeService {

    ChallengeAttempt verifyAttempt(ChallengeAttemptDTO resultAttempt);

    List<ChallengeAttempt> getStatsForUser(String alias);
}
