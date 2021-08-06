package com.example.aluraflix.service;

import com.example.aluraflix.model.Category;
import com.example.aluraflix.repository.CategoryRepository;
import com.example.aluraflix.repository.VideoRepository;
import com.example.aluraflix.resource.category.CategoryReqPost;
import com.example.aluraflix.resource.category.CategoryRespGet;
import com.example.aluraflix.resource.entity.GenericResponse;
import com.example.aluraflix.spec.CategorySpec;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CategoryService implements CategorySpec {

    private final CategoryRepository repository;
    private final VideoRepository videoRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CategoryService(final CategoryRepository repository,
                           final VideoRepository videoRepository,
                           final ModelMapper modelMapper) {
        this.repository = repository;
        this.videoRepository = videoRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public GenericResponse<List<CategoryRespGet>> findAll() {
        try {
            var respGets = repository.findAll()
                    .stream()
                    .map(source -> modelMapper.map(source, CategoryRespGet.class))
                    .collect(Collectors.toList());

            return GenericResponse.<List<CategoryRespGet>>builder()
                    .isOk(true)
                    .object(respGets)
                    .build();
        } catch (Exception e) {
            log.debug("Erro ao buscar as categorias");
            e.printStackTrace();
            return GenericResponse.<List<CategoryRespGet>>builder()
                    .isInternalError(true)
                    .build();
        }
    }

    @Override
    public GenericResponse<CategoryRespGet> create(CategoryReqPost request) {
        try {
            var category = modelMapper.map(request, Category.class);
            repository.save(category);
            var categoryRespGet = modelMapper.map(category, CategoryRespGet.class);
            return GenericResponse.<CategoryRespGet>builder()
                    .isCreated(true)
                    .object(categoryRespGet)
                    .build();
        } catch (Exception e) {
            log.debug("Erro ao criar categoria");
            e.printStackTrace();
            return GenericResponse.<CategoryRespGet>builder()
                    .isInternalError(true)
                    .build();
        }
    }

    @Override
    public GenericResponse<CategoryRespGet> findById(Integer id) {
        var categoryOptional = repository.findById(id);

        if (categoryOptional.isEmpty())
            return GenericResponse.<CategoryRespGet>builder()
                    .isNotFound(true)
                    .build();

        return GenericResponse.<CategoryRespGet>builder()
                .isOk(true)
                .object(modelMapper.map(categoryOptional.get(), CategoryRespGet.class))
                .build();
    }

    @Override
    public GenericResponse<CategoryRespGet> update(Integer id, CategoryReqPost request) {
        try {
            var optional = repository.findById(id);
            if (optional.isEmpty())
                return GenericResponse.<CategoryRespGet>builder()
                        .isNotFound(true)
                        .build();

            var savedCategory = optional.get();
            savedCategory.setTitle(request.getTitle());
            savedCategory.setColor(request.getColor());
            repository.save(savedCategory);

            return GenericResponse.<CategoryRespGet>builder()
                    .isOk(true)
                    .object(modelMapper.map(savedCategory, CategoryRespGet.class))
                    .build();
        } catch (Exception e) {
            log.debug("Erro ao excluir a categoria id {}", id);
            e.printStackTrace();
            return GenericResponse.<CategoryRespGet>builder()
                    .isInternalError(true)
                    .build();
        }

    }

    @Override
    public GenericResponse<CategoryRespGet> delete(Integer id) {
        try {
            var categoryOptional = repository.findById(id);

            if (categoryOptional.isEmpty())
                return GenericResponse.<CategoryRespGet>builder()
                        .isNotFound(true)
                        .build();

            repository.deleteById(id);

            return GenericResponse.<CategoryRespGet>builder()
                    .isOk(true)
                    .build();

        } catch (Exception e) {
            log.debug("Erro ao excluir a categoria id {}", id);
            e.printStackTrace();
            return GenericResponse.<CategoryRespGet>builder()
                    .isInternalError(true)
                    .build();
        }
    }

}
