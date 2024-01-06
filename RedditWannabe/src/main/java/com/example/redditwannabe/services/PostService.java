package com.example.redditwannabe.services;

import com.example.redditwannabe.dtos.PostDTO;
import com.example.redditwannabe.models.Post;

import java.util.List;

public interface PostService {
    List<Post> getAllPosts();

    void savePost(Post post);

    Post PostDTOConverter(PostDTO postDTO, String username);

    Post createNewPost(Post post);

    Post getPostById(Long id);

    void editPost(PostDTO postDTO);
}
