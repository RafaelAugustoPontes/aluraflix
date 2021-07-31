package com.example.aluraflix.spec;

import com.example.aluraflix.resource.category.CategoryReqPost;
import com.example.aluraflix.resource.category.CategoryRespGet;
import com.example.aluraflix.resource.video.VideoRespGet;

import java.util.List;

public interface CategorySpec {

    List<CategoryRespGet> findAll();

    CategoryRespGet create(CategoryReqPost request);

    CategoryRespGet findById(Integer id);

    CategoryRespGet update(Integer id, CategoryReqPost request);

    boolean delete(Integer id);

    List<VideoRespGet> findCategoryVideos(Integer id);
}
