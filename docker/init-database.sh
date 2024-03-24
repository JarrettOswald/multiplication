#!/bin/bash
psql -U postgres <<-EOSQL

CREATE TABLE Challenge_User (
    id BIGINT PRIMARY KEY,
    alias VARCHAR(255)
);

CREATE TABLE Challenge_Attempt (
    id BIGINT PRIMARY KEY,
    CHALLENGE_USER_ID BIGINT,
    factorA INT,
    factorB INT,
    resultAttempt INT,
    correct BOOLEAN,
    FOREIGN KEY (CHALLENGE_USER_ID) REFERENCES Challenge_User(id)
);

EOSQL