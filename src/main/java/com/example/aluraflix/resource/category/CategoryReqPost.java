package com.example.aluraflix.resource.category;

import javax.validation.constraints.NotBlank;

public class CategoryReqPost {

    @NotBlank
    private String title;

    @NotBlank
    private String color;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

}
