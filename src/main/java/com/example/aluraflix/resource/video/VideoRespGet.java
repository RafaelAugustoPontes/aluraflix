package com.example.aluraflix.resource.video;

import com.example.aluraflix.model.Video;

public class VideoRespGet {

    private Integer id;
    private String description;
    private String url;
    private Integer idCategory;
    private String titleCategory;


    public VideoRespGet(Video video) {
        this.id = video.getId();
        this.description = video.getDescription();
        this.url = video.getUrl();
        idCategory = video.getCategory().getId();
        titleCategory = video.getCategory().getTitle();
    }

    public VideoRespGet() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Integer idCategory) {
        this.idCategory = idCategory;
    }

    public String getTitleCategory() {
        return titleCategory;
    }

    public void setTitleCategory(String titleCategory) {
        this.titleCategory = titleCategory;
    }

}