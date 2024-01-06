package com.example.redditwannabe.services;

import com.example.redditwannabe.models.User;
import com.example.redditwannabe.models.Vote;
import com.example.redditwannabe.repositories.PostRepository;
import com.example.redditwannabe.repositories.UserRepository;
import com.example.redditwannabe.repositories.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoteServiceImpl implements VoteService {
    private VoteRepository voteRepository;
    private UserRepository userRepository;
    private PostRepository postRepository;

    @Autowired
    public VoteServiceImpl(VoteRepository voteRepository, UserRepository userRepository, PostRepository postRepository) {
        this.voteRepository = voteRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @Override
    public void votingUp(String username, Long id) {
        Vote vote = postRepository.getPostById(id).getVote();
        User user = userRepository.getUserByUsername(username);
        if (!vote.getUsers().contains(user)) {
            vote.setNumberOfVotes(vote.getNumberOfVotes() + 1);
            vote.getUsers().add(user);
            voteRepository.save(vote);
        }
    }

    @Override
    public void votingDown(String username, Long id) {
        Vote vote = postRepository.getPostById(id).getVote();
        User user = userRepository.getUserByUsername(username);
        if (!vote.getUsers().contains(user) && vote.getNumberOfVotes() != 0) {
            vote.setNumberOfVotes(vote.getNumberOfVotes() - 1);
            vote.getUsers().add(user);
            voteRepository.save(vote);
        }
    }
}
