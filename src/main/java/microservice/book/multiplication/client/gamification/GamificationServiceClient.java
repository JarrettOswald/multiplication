package microservice.book.multiplication.client.gamification;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.book.multiplication.model.ChallengeAttempt;
import microservice.book.multiplication.model.ChallengeSolvedEvent;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class GamificationServiceClient {

    private final GamificationServiceClientProperties properties;
    private final RestTemplate restTemplate;

    public boolean sendAttempt(final ChallengeAttempt attempt) {
        try {
            ChallengeSolvedEvent dto = new ChallengeSolvedEvent(attempt.getId(),
                    attempt.isCorrect(),
                    attempt.getFactorA(),
                    attempt.getFactorB(),
                    attempt.getChallengeUser().getId(),
                    attempt.getChallengeUser().getAlias());
            ResponseEntity<String> response = restTemplate.postForEntity(
                    properties.getUrl() + "/attempts", dto, String.class
            );
            return response.getStatusCode().is2xxSuccessful();
        } catch (Exception e) {
            log.error("There was a problem sending the attempt.", e);
            return false;
        }
    }
}
