package com.example.redditwannabe.services;

import com.example.redditwannabe.dtos.PostDTO;
import com.example.redditwannabe.models.Post;
import com.example.redditwannabe.models.Vote;
import com.example.redditwannabe.repositories.PostRepository;
import com.example.redditwannabe.repositories.UserRepository;
import com.example.redditwannabe.repositories.VoteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private PostRepository postRepository;
    private VoteRepository voteRepository;
    private UserRepository userRepository;

    @Autowired
    public PostService(PostRepository postRepository, VoteRepository voteRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.voteRepository = voteRepository;
        this.userRepository = userRepository;
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public void savePost(Post post) {
        postRepository.save(post);
    }

    public Post PostDTOConverter(PostDTO postDTO, String username) {
        return new Post(postDTO.getName(), postDTO.getUrl(), postDTO.getDescription(), userRepository.getUserByUsername(username));
    }
    public Post createNewPost(Post post) {
        Vote vote = new Vote(0, post);
        voteRepository.save(vote);
        post.setVote(vote);
        return post;
    }
    public Post getPostById(Long id) {
        return postRepository.getPostById(id);
    }

    public void editPost(PostDTO postDTO) {
        Post newPost = postRepository.getPostById(postDTO.getId());
        newPost.setName(postDTO.getName());
        newPost.setDescription(postDTO.getDescription());
        newPost.setUrl(postDTO.getUrl());
        savePost(newPost);
    }
}
