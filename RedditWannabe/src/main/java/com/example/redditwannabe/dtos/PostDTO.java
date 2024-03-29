package com.example.redditwannabe.dtos;

import com.example.redditwannabe.models.User;
import com.example.redditwannabe.models.Vote;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class PostDTO {
    private Long id;
    private String name;
    private String url;
    private String description;

    public PostDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
