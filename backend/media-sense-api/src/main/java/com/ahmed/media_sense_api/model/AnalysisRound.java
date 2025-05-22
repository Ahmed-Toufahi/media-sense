package com.ahmed.media_sense_api.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
public class AnalysisRound {

    @Id
    private Long id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private boolean isActive;
    private String summary;

    @OneToMany(mappedBy = "analysisRound", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Article> articles;


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
