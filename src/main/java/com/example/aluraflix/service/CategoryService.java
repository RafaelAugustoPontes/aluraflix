package com.example.aluraflix.service;

import com.example.aluraflix.repository.CategoryRepository;
import com.example.aluraflix.resource.category.CategoryReqPost;
import com.example.aluraflix.resource.category.CategoryRespGet;
import com.example.aluraflix.spec.CrudSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService implements CrudSpec<CategoryRespGet, CategoryReqPost> {

    private final CategoryRepository repository;

    @Autowired
    public CategoryService(final CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<CategoryRespGet> findAll() {
        return repository.findAll()
                .stream()
                .map(CategoryRespGet::new)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryRespGet create(CategoryReqPost request) {
        return null;
    }

    @Override
    public CategoryRespGet findById(Integer id) {
        return null;
    }

    @Override
    public CategoryRespGet update(Integer id, CategoryReqPost request) {
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

}
