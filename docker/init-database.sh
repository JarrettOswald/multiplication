#!/bin/bash
psql -U postgres <<-EOSQL

CREATE TABLE ChallengeUser (
    id SERIAL PRIMARY KEY,
    alias VARCHAR(255)
);

CREATE TABLE ChallengeAttempt (
    id SERIAL PRIMARY KEY,
    CHALLENGE_USER_ID BIGINT,
    factorA INT,
    factorB INT,
    resultAttempt INT,
    correct BOOLEAN,
    FOREIGN KEY (CHALLENGE_USER_ID) REFERENCES ChallengeUser(id)
);

EOSQL