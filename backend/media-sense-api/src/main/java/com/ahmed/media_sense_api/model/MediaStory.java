package com.ahmed.media_sense_api.model;

import com.ahmed.media_sense_api.enums.UpdateFrequency;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity()
public class MediaStory {
    @Id
    private int id;
    private String title;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;



    @Enumerated(EnumType.STRING)
    private UpdateFrequency updateFrequency;

    @OneToMany(mappedBy = "mediaStory", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Query> queries;

    @OneToOne(mappedBy = "mediaStory", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private GlobalInsight globalInsight;

    public GlobalInsight getGlobalInsight() {
        return globalInsight;
    }

    public void setGlobalInsight(GlobalInsight globalInsight) {
        this.globalInsight = globalInsight;
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public UpdateFrequency getUpdateFrequency() {
        return updateFrequency;
    }

    public void setUpdateFrequency(UpdateFrequency updateFrequency) {
        this.updateFrequency = updateFrequency;
    }

    public List<Query> getQueries() {
        return queries;
    }

    public void setQueries(List<Query> queries) {
        this.queries = queries;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
