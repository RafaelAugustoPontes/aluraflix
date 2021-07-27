package com.example.aluraflix.resource.video;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class VideoReqPost {

    @NotNull @NotEmpty
    private String description;

    @NotNull @NotEmpty
    private String url;

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
