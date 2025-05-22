package com.ahmed.media_sense_api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Query {
    @Id
    private Long id;
    private String queryText;

    @ManyToOne
    @JoinColumn(name = "media_story_id")
    private MediaStory mediaStory;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getQueryText() {
        return queryText;
    }

    public void setQueryText(String queryText) {
        this.queryText = queryText;
    }

    public MediaStory getMediaStory() {
        return mediaStory;
    }

    public void setMediaStory(MediaStory mediaStory) {
        this.mediaStory = mediaStory;
    }
}
