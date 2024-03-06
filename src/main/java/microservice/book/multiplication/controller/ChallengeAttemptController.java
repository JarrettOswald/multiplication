package microservice.book.multiplication.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.book.multiplication.model.ChallengeAttempt;
import microservice.book.multiplication.model.ChallengeAttemptDTO;
import microservice.book.multiplication.service.ChallengeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/attempts")
public class ChallengeAttemptController {

    private final ChallengeService challengeService;

    @PostMapping
    ResponseEntity<ChallengeAttempt> postResult(@RequestBody @Valid ChallengeAttemptDTO challengeAttemptDTO) {
        return ResponseEntity.ok(challengeService.verifyAttempt(challengeAttemptDTO));
    }

    @GetMapping
    ResponseEntity<List<ChallengeAttempt>> getLastAttempt(@PathVariable String alias) {
        return ResponseEntity.ok(challengeService.getLastAttempt(alias));
    }
}
