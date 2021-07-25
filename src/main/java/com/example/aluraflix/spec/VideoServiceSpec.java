package com.example.aluraflix.spec;

import com.example.aluraflix.resource.entity.VideoReqPost;
import com.example.aluraflix.resource.entity.VideoRespGet;

import java.util.List;

public interface VideoServiceSpec {

    List<VideoRespGet> findAll();
    VideoRespGet create(VideoReqPost request);
    VideoRespGet findById(Integer id);
    VideoRespGet update(Integer id, VideoReqPost request);
    boolean delete(Integer id);
}
