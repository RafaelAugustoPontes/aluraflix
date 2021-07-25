package com.example.aluraflix.spec;

import com.example.aluraflix.service.VideoReqPost;
import com.example.aluraflix.service.VideoRespGet;

import java.util.List;

public interface VideoServiceSpec {

    List<VideoRespGet> findAll();
    VideoRespGet create(VideoReqPost request);
    VideoRespGet findById(Integer id);
    VideoRespGet update(Integer id, VideoReqPost request);
    boolean delete(Integer id);
}
