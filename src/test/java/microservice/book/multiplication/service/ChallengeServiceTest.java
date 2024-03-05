package microservice.book.multiplication.service;

import microservice.book.multiplication.model.ChallengeAttempt;
import microservice.book.multiplication.model.ChallengeAttemptDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.BDDAssertions.then;

class ChallengeServiceTest {
    private ChallengeService challengeService;

    @BeforeEach
    public void setUp() {
        challengeService = new ChallengeServiceImpl();
    }

    @Test
    void checkCorrectAttemptTest() {
        ChallengeAttemptDTO attemptDTO = new ChallengeAttemptDTO(50, 60, "john_doe", 3000);
        ChallengeAttempt resultAttempt = challengeService.verifyAttempt(attemptDTO);
        then(resultAttempt.isCorrect()).isTrue();
    }

    @Test
    void checkWrongAttemptTest() {
        ChallengeAttemptDTO attemptDTO = new ChallengeAttemptDTO(50, 60, "john_doe", 5000);
        ChallengeAttempt resultAttempt = challengeService.verifyAttempt(attemptDTO);
        then(resultAttempt.isCorrect()).isFalse();
    }
}