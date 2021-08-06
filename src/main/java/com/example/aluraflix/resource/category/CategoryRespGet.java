package com.example.aluraflix.resource.category;

import com.example.aluraflix.resource.entity.ResponseContent;

public class CategoryRespGet implements ResponseContent {

    private Integer id;
    private String title;
    private String color;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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
