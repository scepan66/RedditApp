package com.example.redditwannabe.models;

import jakarta.persistence.*;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String url;
    private String description;
    @OneToOne
    @JoinColumn(name = "vote_id", referencedColumnName = "id")
    private Vote vote;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Post(Long id, String name, String description, Vote vote, String url) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.vote = vote;
        this.url = url;
    }

    public Post(String name, String url, String description, User user) {
        this.name = name;
        this.url = url;
        this.description = description;
        this.user = user;
    }

    public Post() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Vote getVote() {
        return vote;
    }

    public void setVote(Vote vote) {
        this.vote = vote;
    }
}
