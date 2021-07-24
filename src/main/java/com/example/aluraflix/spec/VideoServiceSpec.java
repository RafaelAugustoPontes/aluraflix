package com.example.aluraflix.spec;

import com.example.aluraflix.model.Video;
import com.example.aluraflix.service.VideoReqPost;
import com.example.aluraflix.service.VideoRespGet;

import java.util.List;

public interface VideoServiceSpec {

    List<Video> findAll();

    VideoRespGet create(VideoReqPost request);
}
