package com.example.aluraflix.spec;

import com.example.aluraflix.resource.category.CategoryReqPost;
import com.example.aluraflix.resource.category.CategoryRespGet;
import com.example.aluraflix.resource.entity.GenericResponse;

import java.util.List;

public interface CategorySpec {

    GenericResponse<List<CategoryRespGet>> findAll();

    GenericResponse<CategoryRespGet> create(CategoryReqPost request);

    GenericResponse<CategoryRespGet> findById(Integer id);

    GenericResponse<CategoryRespGet> update(Integer id, CategoryReqPost request);

    GenericResponse<CategoryRespGet> delete(Integer id);

}
