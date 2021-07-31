package com.example.aluraflix.resource.video;

import com.example.aluraflix.spec.VideoSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

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
        var videos = service.findAll();

        return ResponseEntity.ok(videos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VideoRespGet> findById(@PathVariable Integer id) {
        var video = service.findById(id);

        if (video == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(video);
    }

    @PostMapping
    public ResponseEntity<VideoRespGet> create(@Valid @RequestBody VideoReqPost request, UriComponentsBuilder uriComponentsBuilder) {
        var videoRespGet = service.create(request);
        var uri = uriComponentsBuilder.path("/videos/{id}").buildAndExpand(videoRespGet.getId()).toUri();

        return ResponseEntity.created(uri).body(videoRespGet);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VideoRespGet> update(@PathVariable Integer id, @Valid @RequestBody VideoReqPost request) {
        var videoRespGet = service.update(id, request);

        return ResponseEntity.ok().body(videoRespGet);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<VideoRespGet> delete(@PathVariable Integer id) {
        if (service.delete(id))
            return ResponseEntity.noContent().build();
        return ResponseEntity.notFound().build();
    }

}
