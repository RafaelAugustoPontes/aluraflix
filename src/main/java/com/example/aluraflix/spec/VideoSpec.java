package com.example.aluraflix.spec;

import com.example.aluraflix.resource.entity.GenericResponse;
import com.example.aluraflix.resource.video.VideoReqPost;
import com.example.aluraflix.resource.video.VideoRespGet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface VideoSpec {

    GenericResponse<Page<VideoRespGet>> find(String description, Pageable page);

    GenericResponse<List<VideoRespGet>> findFree();

    GenericResponse<VideoRespGet> create(VideoReqPost request);

    GenericResponse<VideoRespGet> findById(Integer id);

    GenericResponse<VideoRespGet> update(Integer id, VideoReqPost request);

    GenericResponse<VideoRespGet> delete(Integer id);

    GenericResponse<List<VideoRespGet>> findVideosByCategoryId(Integer id);
}
