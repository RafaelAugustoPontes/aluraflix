package com.example.aluraflix.service;

import com.example.aluraflix.model.Video;
import com.example.aluraflix.repository.VideoRepository;
import com.example.aluraflix.spec.VideoServiceSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoService implements VideoServiceSpec {

    private VideoRepository repository;

    @Autowired
    public VideoService(VideoRepository repository) {
        this.repository = repository;
    }

    public List<Video> findAll() {
        return repository.findAll();
    }

    @Override
    public VideoRespGet create(VideoReqPost request) {
        var video = new Video();
        video.setDescription(request.getDescription());
        video.setUrl(request.getUrl());

        var createdVideo = repository.save(video);

        return new VideoRespGet(createdVideo);
    }


}
