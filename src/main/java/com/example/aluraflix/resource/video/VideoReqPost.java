package com.example.aluraflix.resource.video;

import javax.validation.constraints.NotBlank;

public class VideoReqPost {

    @NotBlank(message = "Descrição - O campo é obrigatório")
    private String description;

    @NotBlank(message = "URL - O campo é obrigatório")
    private String url;

    private Integer idCategory;

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
        return idCategory == null ? 1 : idCategory;
    }

    public void setIdCategory(Integer idCategory) {
        this.idCategory = idCategory;
    }
}
