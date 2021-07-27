package com.example.aluraflix.service;

import com.example.aluraflix.model.Video;
import com.example.aluraflix.repository.VideoRepository;
import com.example.aluraflix.resource.video.VideoReqPost;
import com.example.aluraflix.resource.video.VideoRespGet;
import com.example.aluraflix.spec.CrudSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VideoService implements CrudSpec<VideoRespGet, VideoReqPost> {

    private final VideoRepository repository;

    @Autowired
    public VideoService(VideoRepository repository) {
        this.repository = repository;
    }

    public List<VideoRespGet> findAll() {
        return repository.findAll()
                .stream()
                .map(VideoRespGet::new)
                .collect(Collectors.toList());
    }

    @Override
    public VideoRespGet create(VideoReqPost request) {
        var video = new Video();
        video.setDescription(request.getDescription());
        video.setUrl(request.getUrl());

        var createdVideo = repository.save(video);

        return new VideoRespGet(createdVideo);
    }

    @Override
    public VideoRespGet findById(Integer id) {
        var video = repository.findById(id);
        return video.map(VideoRespGet::new).orElse(null);
    }

    @Override
    public VideoRespGet update(Integer id, VideoReqPost request) {
        var optionalVideo = repository.findById(id);

        if (optionalVideo.isEmpty())
            return null;

        var savedVideo = optionalVideo.get();
        savedVideo.setUrl(request.getUrl());
        savedVideo.setDescription(request.getDescription());

        repository.save(savedVideo);

        return new VideoRespGet(savedVideo);
    }

    @Override
    public boolean delete(Integer id) {
        var video = repository.findById(id);
        if (video.isEmpty())
            return false;

        repository.deleteById(id);

        return true;
    }


}
