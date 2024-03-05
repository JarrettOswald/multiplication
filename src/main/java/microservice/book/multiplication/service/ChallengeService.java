package microservice.book.multiplication.service;


import microservice.book.multiplication.model.ChallengeAttempt;
import microservice.book.multiplication.model.ChallengeAttemptDTO;

public interface ChallengeService {

    ChallengeAttempt verifyAttempt(ChallengeAttemptDTO resultAttempt);
}
