package microservice.book.multiplication.challenge.model;


import jakarta.persistence.*;
import lombok.*;
import microservice.book.multiplication.user.User;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ChallengeAttempt {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User userId;

    private int factorA;

    private int factorB;

    private int resultAttempt;

    private boolean correct;
}
