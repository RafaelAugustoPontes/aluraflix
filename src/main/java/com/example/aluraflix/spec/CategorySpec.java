package com.example.aluraflix.spec;

import com.example.aluraflix.resource.category.CategoryReqPost;
import com.example.aluraflix.resource.category.CategoryRespGet;
import com.example.aluraflix.resource.entity.GenericResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategorySpec {

    GenericResponse<Page<CategoryRespGet>> findAll(Pageable page);

    GenericResponse<CategoryRespGet> create(CategoryReqPost request);

    GenericResponse<CategoryRespGet> findById(Integer id);

    GenericResponse<CategoryRespGet> update(Integer id, CategoryReqPost request);

    GenericResponse<CategoryRespGet> delete(Integer id);

}
