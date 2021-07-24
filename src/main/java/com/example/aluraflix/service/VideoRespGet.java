package com.example.aluraflix.service;

import com.example.aluraflix.model.Video;

public class VideoRespGet {

    private String description;
    private String url;

    public VideoRespGet(Video video) {
        this.description = video.getDescription();
        this.url = video.getUrl();
    }

    public VideoRespGet() {

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
