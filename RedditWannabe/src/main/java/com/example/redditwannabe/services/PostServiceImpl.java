package com.example.redditwannabe.services;

import com.example.redditwannabe.dtos.PostDTO;
import com.example.redditwannabe.models.Post;
import com.example.redditwannabe.models.Vote;
import com.example.redditwannabe.repositories.PostRepository;
import com.example.redditwannabe.repositories.UserRepository;
import com.example.redditwannabe.repositories.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    private PostRepository postRepository;
    private VoteRepository voteRepository;
    private UserRepository userRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, VoteRepository voteRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.voteRepository = voteRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public void savePost(Post post) {
        postRepository.save(post);
    }

    @Override
    public Post PostDTOConverter(PostDTO postDTO, String username) {
        return new Post(postDTO.getName(), postDTO.getUrl(), postDTO.getDescription(), userRepository.getUserByUsername(username));
    }
    @Override
    public Post createNewPost(Post post) {
        Vote vote = new Vote(0, post);
        voteRepository.save(vote);
        post.setVote(vote);
        return post;
    }
    @Override
    public Post getPostById(Long id) {
        return postRepository.getPostById(id);
    }

    @Override
    public void editPost(PostDTO postDTO) {
        Post newPost = postRepository.getPostById(postDTO.getId());
        newPost.setName(postDTO.getName());
        newPost.setDescription(postDTO.getDescription());
        newPost.setUrl(postDTO.getUrl());
        savePost(newPost);
    }
}
