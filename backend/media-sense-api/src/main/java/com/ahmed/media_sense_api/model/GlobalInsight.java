package com.ahmed.media_sense_api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class GlobalInsight {
    @Id
    private long id;
    private String insight;

    @OneToOne
    @JoinColumn(name = "media_story_id", nullable = false, unique = true)
    private MediaStory mediaStory;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getInsight() {
        return insight;
    }

    public void setInsight(String insight) {
        this.insight = insight;
    }

    public MediaStory getMediaStory() {
        return mediaStory;
    }

    public void setMediaStory(MediaStory mediaStory) {
        this.mediaStory = mediaStory;
    }
}
