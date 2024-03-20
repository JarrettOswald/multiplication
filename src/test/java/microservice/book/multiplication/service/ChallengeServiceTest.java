package microservice.book.multiplication.service;

import microservice.book.multiplication.client.gamification.GamificationServiceClient;
import microservice.book.multiplication.model.ChallengeAttempt;
import microservice.book.multiplication.model.ChallengeAttemptDTO;
import microservice.book.multiplication.model.ChallengeUser;
import microservice.book.multiplication.repository.ChallengeAttemptRepository;
import microservice.book.multiplication.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ChallengeServiceTest {
    private ChallengeService challengeService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ChallengeAttemptRepository attemptRepository;

    @Mock
    private GamificationServiceClient gamificationServiceClient;

    @BeforeEach
    public void setUp() {
        challengeService = new ChallengeServiceImpl(
                userRepository,
                attemptRepository,
                gamificationServiceClient
        );
    }

    @Test
    void checkCorrectAttemptTest() {
        given(attemptRepository.save(any())).will(returnsFirstArg());
        ChallengeAttemptDTO attemptDTO = new ChallengeAttemptDTO(50, 60, "john_doe", 3000);
        ChallengeAttempt resultAttempt = challengeService.verifyAttempt(attemptDTO);
        then(resultAttempt.isCorrect()).isTrue();

        verify(userRepository).save(new ChallengeUser("john_doe"));
        verify(attemptRepository).save(resultAttempt);
    }

    @Test
    void checkExistingUserTest() {
        given(attemptRepository.save(any())).will(returnsFirstArg());
        ChallengeUser existUser = new ChallengeUser(1L, "john_doe");
        given(userRepository.findByAlias("john_doe")).willReturn(Optional.of(existUser));
        ChallengeAttemptDTO attemptDTO = new ChallengeAttemptDTO(50, 60, "john_doe", 5000);

        ChallengeAttempt resultAttempt = challengeService.verifyAttempt(attemptDTO);

        then(resultAttempt.isCorrect()).isFalse();
        then(resultAttempt.getChallengeUser()).isEqualTo(existUser);
        verify(userRepository, never()).save(any());
        verify(attemptRepository).save(resultAttempt);
    }

    @Test
    void checkLastAttempt() {
        List<ChallengeAttempt> attemptList = List.of(
                new ChallengeAttempt(1L, new ChallengeUser("john_doe"), 50, 50, 2000, false),
                new ChallengeAttempt(2L, new ChallengeUser("john_doe"), 50, 51, 2050, false),
                new ChallengeAttempt(3L, new ChallengeUser("john_doe"), 50, 50, 2500, true)
        );
        given(attemptRepository.findTop10ByChallengeUserAliasOrderByIdDesc("john_doe")).willReturn(attemptList);
        List<ChallengeAttempt> resultAttempt = challengeService.getStatsForUser("john_doe");
        then(attemptList).isEqualTo(resultAttempt);
    }
}