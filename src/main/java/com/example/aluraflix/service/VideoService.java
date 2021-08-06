package com.example.aluraflix.service;

import com.example.aluraflix.model.Video;
import com.example.aluraflix.repository.CategoryRepository;
import com.example.aluraflix.repository.VideoRepository;
import com.example.aluraflix.resource.entity.GenericResponse;
import com.example.aluraflix.resource.video.VideoReqPost;
import com.example.aluraflix.resource.video.VideoRespGet;
import com.example.aluraflix.spec.VideoSpec;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class VideoService implements VideoSpec {

    private final VideoRepository repository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public VideoService(final VideoRepository repository,
                        final CategoryRepository categoryRepository,
                        final ModelMapper modelMapper) {
        this.repository = repository;
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public GenericResponse<Page<VideoRespGet>> find(String description, Pageable page) {
        try {
            Page<Video> videoPage;
            if (StringUtils.isBlank(description))
                videoPage = repository.findAll(page);
            else
                videoPage = repository.findByDescriptionContainingIgnoreCase(description, page);

            Page<VideoRespGet> respGetList = videoPage.map(video -> modelMapper.map(video, VideoRespGet.class));


            return GenericResponse.<Page<VideoRespGet>>builder()
                    .isOk(true)
                    .object(respGetList)
                    .build();
        } catch (Exception e) {
            log.debug("Erro ao buscar os videos");
            e.printStackTrace();
            return GenericResponse.<Page<VideoRespGet>>builder()
                    .isInternalError(true)
                    .build();
        }
    }

    @Override
    @Transactional
    public GenericResponse<VideoRespGet> create(VideoReqPost request) {
        try {
            var video = modelMapper.map(request, Video.class);
            final var idCategory = request.getCategoryId() == null ? Integer.valueOf(1) : request.getCategoryId();
            video.setCategory(categoryRepository.findById(idCategory).orElseThrow());
            var createdVideo = repository.save(video);
            var resp = modelMapper.map(createdVideo, VideoRespGet.class);

            return GenericResponse.<VideoRespGet>builder()
                    .isCreated(true)
                    .object(resp)
                    .build();
        } catch (Exception e) {
            log.debug("Erro ao criar o vídeo");
            e.printStackTrace();
            return GenericResponse.<VideoRespGet>builder()
                    .isInternalError(true)
                    .build();
        }
    }

    @Override
    public GenericResponse<VideoRespGet> findById(Integer id) {
        try {
            var optionalVideo = repository.findById(id);

            if (optionalVideo.isEmpty())
                return GenericResponse.<VideoRespGet>builder()
                        .isNotFound(true)
                        .build();

            return GenericResponse.<VideoRespGet>builder()
                    .isOk(true)
                    .object(modelMapper.map(optionalVideo.get(), VideoRespGet.class))
                    .build();
        } catch (Exception e) {
            log.debug("Erro ao buscar o vídeo id {}", id);
            e.printStackTrace();
            return GenericResponse.<VideoRespGet>builder()
                    .isInternalError(true)
                    .build();
        }
    }

    @Override
    public GenericResponse<VideoRespGet> update(Integer id, VideoReqPost request) {
        try {
            var optionalVideo = repository.findById(id);

            if (optionalVideo.isEmpty()) {
                return GenericResponse.<VideoRespGet>builder()
                        .isNotFound(true)
                        .build();
            }

            var savedVideo = optionalVideo.get();
            savedVideo.setUrl(request.getUrl());
            savedVideo.setDescription(request.getDescription());
            repository.save(savedVideo);

            return GenericResponse.<VideoRespGet>builder()
                    .isOk(true)
                    .object(modelMapper.map(optionalVideo.get(), VideoRespGet.class))
                    .build();
        } catch (Exception e) {
            log.debug("Erro ao atualizar o vídeo id {}", id);
            e.printStackTrace();
            return GenericResponse.<VideoRespGet>builder()
                    .isInternalError(true)
                    .build();
        }
    }

    @Override
    public GenericResponse<VideoRespGet> delete(Integer id) {
        try {
            var video = repository.findById(id);
            if (video.isEmpty())
                return GenericResponse.<VideoRespGet>builder()
                        .isNotFound(true)
                        .build();

            repository.deleteById(id);
            return GenericResponse.<VideoRespGet>builder()
                    .isOk(true)
                    .build();
        } catch (Exception e) {
            log.debug("Erro ao excluir o vídeo id {}", id);
            e.printStackTrace();
            return GenericResponse.<VideoRespGet>builder()
                    .isInternalError(true)
                    .build();
        }
    }

    @Override
    public GenericResponse<List<VideoRespGet>> findVideosByCategoryId(Integer id) {
        var videos = repository.findByCategoryId(id);

        if (videos.isEmpty())
            return GenericResponse.<List<VideoRespGet>>builder()
                    .isNotFound(true)
                    .build();

        var respGets = videos.stream()
                .map(source -> modelMapper.map(source, VideoRespGet.class))
                .collect(Collectors.toList());

        return GenericResponse.<List<VideoRespGet>>builder()
                .isOk(true)
                .object(respGets)
                .build();
    }

}
