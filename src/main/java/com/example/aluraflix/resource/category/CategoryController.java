package com.example.aluraflix.resource.category;

import com.example.aluraflix.spec.CrudSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
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
