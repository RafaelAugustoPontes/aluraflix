package com.example.aluraflix.service;

import com.example.aluraflix.model.Category;
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
        var category = new Category();
        category.setTitle(request.getTitle());
        category.setColor(request.getColor());

        repository.save(category);

        return new CategoryRespGet(category);
    }

    @Override
    public CategoryRespGet findById(Integer id) {
        var categoryOptional = repository.findById(id);

        if (categoryOptional.isEmpty())
            return null;

        return new CategoryRespGet(categoryOptional.get());
    }

    @Override
    public CategoryRespGet update(Integer id, CategoryReqPost request) {
        var optional = repository.findById(id);

        if (optional.isEmpty())
            return null;

        var savedCategory = optional.get();

        savedCategory.setTitle(request.getTitle());
        savedCategory.setColor(request.getColor());

        repository.save(savedCategory);

        return new CategoryRespGet(savedCategory);
    }

    @Override
    public boolean delete(Integer id) {
        var categoryOptional = repository.findById(id);

        if (categoryOptional.isEmpty())
            return false;

        repository.deleteById(id);

        return true;
    }

}
