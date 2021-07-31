package com.example.aluraflix.resource.category;

import com.example.aluraflix.resource.video.VideoRespGet;
import com.example.aluraflix.spec.CategorySpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategorySpec service;

    @Autowired
    public CategoryController(CategorySpec service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<CategoryRespGet>> findAll() {
        var categories = service.findAll();

        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryRespGet> findById(@PathVariable Integer id) {
        var category = service.findById(id);

        if (category == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(category);
    }

    @GetMapping("/{id}/videos")
    public ResponseEntity<List<VideoRespGet>> findVideosByIdCategory(@PathVariable Integer id) {
        var videos = service.findCategoryVideos(id);

        if (videos == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(videos);
    }

    @PostMapping
    public ResponseEntity<CategoryRespGet> create(@Valid @RequestBody CategoryReqPost request, UriComponentsBuilder uriComponentsBuilder) {
        var categoryRespGet = service.create(request);
        var uri = uriComponentsBuilder.path("/categories/{id}").buildAndExpand(categoryRespGet.getId()).toUri();

        return ResponseEntity.created(uri).body(categoryRespGet);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryRespGet> update(@PathVariable Integer id, @Valid @RequestBody CategoryReqPost request) {
        var rpsGet = service.update(id, request);

        if (rpsGet == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok().body(rpsGet);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CategoryRespGet> delete(@PathVariable Integer id) {
        if (service.delete(id))
            return ResponseEntity.noContent().build();
        return ResponseEntity.notFound().build();
    }


}
