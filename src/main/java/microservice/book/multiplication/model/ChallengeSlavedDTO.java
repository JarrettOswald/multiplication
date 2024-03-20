package microservice.book.multiplication.model;

import lombok.Value;

@Value
public class ChallengeSlavedDTO {

    long attemptId;
    boolean correct;
    int factorA;
    int factorB;
    long userId;
    String userAlias;
}