package com.example.aluraflix.spec;

import com.example.aluraflix.resource.video.VideoReqPost;
import com.example.aluraflix.resource.video.VideoRespGet;

import java.util.List;

public interface VideoSpec {

    List<VideoRespGet> findAll();

    VideoRespGet create(VideoReqPost request);

    VideoRespGet findById(Integer id);

    VideoRespGet update(Integer id, VideoReqPost request);

    boolean delete(Integer id);

}
