package microservice.book.multiplication.challenge.service;

import microservice.book.multiplication.challenge.model.ChallengeAttempt;
import microservice.book.multiplication.challenge.model.ChallengeAttemptDTO;

public interface ChallengeService {

    ChallengeAttempt verifyAttempt(ChallengeAttemptDTO resultAttempt);
}
