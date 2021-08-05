package com.example.aluraflix.spec;

import com.example.aluraflix.resource.entity.GenericResponse;
import com.example.aluraflix.resource.video.VideoReqPost;
import com.example.aluraflix.resource.video.VideoRespGet;

import java.util.List;

public interface VideoSpec {

    GenericResponse<List<VideoRespGet>> find(String description);

    GenericResponse<VideoRespGet> create(VideoReqPost request);

    GenericResponse<VideoRespGet> findById(Integer id);

    GenericResponse<VideoRespGet> update(Integer id, VideoReqPost request);

    GenericResponse<VideoRespGet> delete(Integer id);

}
