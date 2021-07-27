package com.example.aluraflix.resource.category;

import com.example.aluraflix.spec.CrudSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CrudSpec<CategoryRespGet, CategoryReqPost> service;

    @Autowired
    public CategoryController(CrudSpec<CategoryRespGet, CategoryReqPost> service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<CategoryRespGet>> findAll() {
        var videos = service.findAll();

        return ResponseEntity.ok(videos);
    }

}
