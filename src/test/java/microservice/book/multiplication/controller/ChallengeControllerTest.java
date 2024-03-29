package microservice.book.multiplication.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ChallengeControllerTest {
    @Autowired
    private ChallengeController challengeController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getRandomChallenge() throws Exception {
        mockMvc.perform(get("/challenges/random"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.factorB").isNumber())
                .andExpect(jsonPath("$.factorA").isNumber());
    }
}