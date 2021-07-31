package com.example.aluraflix.resource.category;

import javax.validation.constraints.NotBlank;

public class CategoryReqPost {

    @NotBlank(message = "Título - O campo é obrigatório")
    private String title;

    @NotBlank(message = "Cor - O campo é obrigatório")
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
