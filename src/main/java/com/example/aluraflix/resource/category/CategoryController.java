package com.example.aluraflix.resource.category;

import com.example.aluraflix.resource.video.VideoRespGet;
import com.example.aluraflix.spec.CategorySpec;
import com.example.aluraflix.spec.VideoSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategorySpec service;
    private final VideoSpec videoService;

    @Autowired
    public CategoryController(CategorySpec service, VideoSpec videoService) {
        this.service = service;
        this.videoService = videoService;
    }

    @GetMapping
    public ResponseEntity<List<CategoryRespGet>> findAll() {
        return service.findAll().generate();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryRespGet> findById(@PathVariable Integer id) {
        return service.findById(id).generate();
    }

    @GetMapping("/{id}/videos")
    public ResponseEntity<List<VideoRespGet>> findVideosByIdCategory(@PathVariable Integer id) {
        return videoService.findVideosByCategoryId(id).generate();
    }

    @PostMapping
    public ResponseEntity<CategoryRespGet> create(@Valid @RequestBody CategoryReqPost request) {
        return service.create(request).generate();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryRespGet> update(@PathVariable Integer id, @Valid @RequestBody CategoryReqPost request) {
        return service.update(id, request).generate();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CategoryRespGet> delete(@PathVariable Integer id) {
        return service.delete(id).generate();
    }


}
