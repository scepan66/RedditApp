package com.example.redditwannabe.services;

public interface VoteService {
    void votingUp(String username, Long id);

    void votingDown(String username, Long id);
}
