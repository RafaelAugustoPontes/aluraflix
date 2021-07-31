package com.example.aluraflix.service;

import com.example.aluraflix.model.Category;
import com.example.aluraflix.repository.CategoryRepository;
import com.example.aluraflix.repository.VideoRepository;
import com.example.aluraflix.resource.category.CategoryReqPost;
import com.example.aluraflix.resource.category.CategoryRespGet;
import com.example.aluraflix.resource.video.VideoRespGet;
import com.example.aluraflix.spec.CategorySpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService implements CategorySpec {

    private final CategoryRepository repository;
    private final VideoRepository videoRepository;

    @Autowired
    public CategoryService(final CategoryRepository repository, final VideoRepository videoRepository) {
        this.repository = repository;
        this.videoRepository = videoRepository;
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

    @Override
    public List<VideoRespGet> findCategoryVideos(Integer id) {
        var videos = videoRepository.findByCategoryId(id);

        if (videos.isEmpty())
            return null;

        return videos.stream()
                .map(VideoRespGet::new)
                .collect(Collectors.toList());
    }

}
