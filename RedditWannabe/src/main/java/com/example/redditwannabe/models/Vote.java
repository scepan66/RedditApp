package com.example.redditwannabe.models;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int numberOfVotes;
    @OneToOne(mappedBy = "vote", cascade = CascadeType.ALL)
    private Post post;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_votes",
            joinColumns = @JoinColumn(name = "vote_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> users;

    public Vote(int numberOfVotes, Post post) {
        this.numberOfVotes = numberOfVotes;
        this.post = post;
    }

    public Vote() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumberOfVotes() {
        return numberOfVotes;
    }

    public void setNumberOfVotes(int numberOfVotes) {
        this.numberOfVotes = numberOfVotes;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
