package microservice.book.multiplication.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.book.multiplication.model.ChallengeAttempt;
import microservice.book.multiplication.model.ChallengeAttemptDTO;
import microservice.book.multiplication.model.User;
import microservice.book.multiplication.repository.ChallengeAttemptRepository;
import microservice.book.multiplication.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChallengeServiceImpl implements ChallengeService {

    private final UserRepository userRepository;
    private final ChallengeAttemptRepository attemptRepository;

    @Override
    public ChallengeAttempt verifyAttempt(ChallengeAttemptDTO attemptDTO) {
        User user = userRepository.findByAlias(attemptDTO.getUserAlias())
                .orElseGet(() -> {
                    log.info("Creating new user with alias {}", attemptDTO.getUserAlias());
                    return userRepository.save(new User(attemptDTO.getUserAlias()));
                });
        boolean isCorrect = attemptDTO.getGuess() == attemptDTO.getFactorA() * attemptDTO.getFactorB();

        ChallengeAttempt checkedAttempt = new ChallengeAttempt(null,
                user,
                attemptDTO.getFactorA(),
                attemptDTO.getFactorB(),
                attemptDTO.getGuess(),
                isCorrect);

        return attemptRepository.save(checkedAttempt);
    }

    @Override
    public List<ChallengeAttempt> getLastAttempt(String alias) {
        return attemptRepository.lastAttempts(alias);
    }
}
