package com.example.redditwannabe.repositories;

import com.example.redditwannabe.models.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long> {}
