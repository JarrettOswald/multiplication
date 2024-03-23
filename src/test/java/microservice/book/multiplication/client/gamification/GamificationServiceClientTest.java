package microservice.book.multiplication.client.gamification;

import com.github.tomakehurst.wiremock.WireMockServer;
import microservice.book.multiplication.model.ChallengeAttempt;
import microservice.book.multiplication.model.ChallengeUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GamificationServiceClientTest {

    @Autowired
    private GamificationServiceClient gamificationServiceClient;

    @BeforeAll
    public static void setUp() {
        WireMockServer wireMockServer = new WireMockServer(8081);
        wireMockServer.start();
    }

    @Test
    void sendAttemptTest() {
        ChallengeUser challengeUser = new ChallengeUser(1L, "Test");
        var challengeAttempt = new ChallengeAttempt(
                1L,
                challengeUser,
                14,
                16,
                224,
                true
        );
        var result = gamificationServiceClient.sendAttempt(challengeAttempt);
        Assertions.assertTrue(result);
    }

    @Test
    void sendAttemptBadResponseTest() {
        ChallengeUser challengeUser = new ChallengeUser(1L, "Test");
        var challengeAttempt = new ChallengeAttempt(
                2L,
                challengeUser,
                14,
                16,
                224,
                true
        );
        var result = gamificationServiceClient.sendAttempt(challengeAttempt);
        Assertions.assertFalse(result);
    }
}