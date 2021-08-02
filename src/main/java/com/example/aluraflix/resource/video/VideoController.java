package com.example.aluraflix.resource.video;

import com.example.aluraflix.spec.VideoSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/videos")
public class VideoController {

    private final VideoSpec service;

    @Autowired
    public VideoController(VideoSpec service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<VideoRespGet>> findAll() {
        return service.findAll().generate();
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
