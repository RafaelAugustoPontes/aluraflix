package com.example.aluraflix.resource;

import com.example.aluraflix.model.Video;
import com.example.aluraflix.service.VideoReqPost;
import com.example.aluraflix.service.VideoRespGet;
import com.example.aluraflix.spec.VideoServiceSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/videos")
public class VideoController {

    public VideoServiceSpec service;

    @Autowired
    public VideoController(VideoServiceSpec service) {
        this.service = service;
    }

    @GetMapping
    public List<Video> findAll() {
        return service.findAll();
    }

    @PostMapping
    public VideoRespGet create(@RequestBody @Valid VideoReqPost request) {
        return service.create(request);
    }

}
