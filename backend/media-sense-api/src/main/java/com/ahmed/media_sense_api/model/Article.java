package com.ahmed.media_sense_api.model;

import jakarta.persistence.*;

@Entity
public class Article {
    @Id
    private Long id;
    private String title;
    private String url;
    private String domainName;
    private String summary;

    @ManyToOne
    @JoinColumn(name = "analysis_round_id")
    private AnalysisRound analysisRound;


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public AnalysisRound getAnalysisRound() {
        return analysisRound;
    }

    public void setAnalysisRound(AnalysisRound analysisRound) {
        this.analysisRound = analysisRound;
    }
}
