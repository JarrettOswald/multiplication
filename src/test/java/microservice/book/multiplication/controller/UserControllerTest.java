package microservice.book.multiplication.controller;

import microservice.book.multiplication.model.ChallengeUser;
import microservice.book.multiplication.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private UserController challengeController;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @Test
    void getUserByIdList() throws Exception {
        var user = new ChallengeUser();
        user.setId(1L);
        user.setAlias("Test");

        given(userRepository.findAllByIdIn(List.of(1L))).willReturn(List.of(user));

        mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$..id").value(1))
                .andExpect(jsonPath("$..alias").value("Test"));
    }

    @Test
    void getUsersByIdsList() throws Exception {
        var user = new ChallengeUser();
        user.setId(1L);
        user.setAlias("Test");

        given(userRepository.findAllByIdIn(List.of(1L))).willReturn(List.of(user));

        mockMvc.perform(get("/users/1,2,3"))
                .andExpect(status().isOk());
    }
}