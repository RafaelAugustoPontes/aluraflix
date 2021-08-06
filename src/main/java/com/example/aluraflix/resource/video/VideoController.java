package com.example.aluraflix.resource.video;

import com.example.aluraflix.spec.VideoSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/videos")
public class VideoController {

    private final VideoSpec service;

    @Autowired
    public VideoController(VideoSpec service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Page<VideoRespGet>> findAll(@RequestParam(required = false) String description,
                                                      @PageableDefault(sort = {"description"})@RequestParam(required = false) Pageable page) {
        return service.find(description, page).generate();
    }

    @GetMapping("/{id}")
    public ResponseEntity<VideoRespGet> findById(@PathVariable Integer id) {
        return service.findById(id).generate();
    }

    @PostMapping
    public ResponseEntity<VideoRespGet> create(@Valid @RequestBody VideoReqPost request) {
        var genericResponse = service.create(request);
        return genericResponse.generate();
    }

    @PutMapping("/{id}")
    public ResponseEntity<VideoRespGet> update(@PathVariable Integer id, @Valid @RequestBody VideoReqPost request) {
        return service.update(id, request).generate();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<VideoRespGet> delete(@PathVariable Integer id) {
        return service.delete(id).generate();
    }

}
