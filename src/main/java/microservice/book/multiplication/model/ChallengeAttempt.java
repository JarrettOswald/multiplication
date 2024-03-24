package microservice.book.multiplication.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ChallengeAttempt {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "CHALLENGE_USER_ID")
    private ChallengeUser challengeUser;

    private int factorA;

    private int factorB;

    private int resultAttempt;

    private boolean correct;
}
