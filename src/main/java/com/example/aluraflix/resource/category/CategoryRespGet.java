package com.example.aluraflix.resource.category;

import com.example.aluraflix.model.Category;

public class CategoryRespGet {

    private Integer id;
    private String title;
    private String color;

    public CategoryRespGet(Category category) {
        this.id = category.getId();
        this.title = category.getTitle();
        this.color = category.getColor();
    }

    public CategoryRespGet() {

    }

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
