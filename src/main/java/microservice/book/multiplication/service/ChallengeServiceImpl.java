package microservice.book.multiplication.service;


import microservice.book.multiplication.model.ChallengeAttempt;

import microservice.book.multiplication.model.ChallengeAttemptDTO;
import microservice.book.multiplication.model.User;
import org.springframework.stereotype.Service;

@Service
public class ChallengeServiceImpl implements ChallengeService {

    @Override
    public ChallengeAttempt verifyAttempt(ChallengeAttemptDTO attemptDTO) {
        boolean isCorrect = attemptDTO.getGuess() == attemptDTO.getFactorA() * attemptDTO.getFactorB();

        User user = new User(null, attemptDTO.getUserAlias());

        return new ChallengeAttempt(
                null,
                user,
                attemptDTO.getFactorA(),
                attemptDTO.getFactorB(),
                attemptDTO.getGuess(),
                isCorrect);
    }
}
