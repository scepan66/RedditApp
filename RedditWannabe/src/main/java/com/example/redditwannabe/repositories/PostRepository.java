package com.example.redditwannabe.repositories;

import com.example.redditwannabe.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    public Post getPostById(Long id);
}
