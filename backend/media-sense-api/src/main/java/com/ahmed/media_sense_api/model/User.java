package com.ahmed.media_sense_api.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Entity(name = "Users")
public class User {
    @Id
    private int id;
    private String username;
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MediaStory> mediaStories = new ArrayList<>();

    public int getId() {
        return id;
    }

    public List<MediaStory> getMediaStories() {
        return mediaStories;
    }

    public void setMediaStories(List<MediaStory> mediaStories) {
        this.mediaStories = mediaStories;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



}

