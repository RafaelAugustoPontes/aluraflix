package com.example.aluraflix.service;

import com.example.aluraflix.model.Video;
import com.example.aluraflix.repository.CategoryRepository;
import com.example.aluraflix.repository.VideoRepository;
import com.example.aluraflix.resource.entity.GenericResponse;
import com.example.aluraflix.resource.video.VideoReqPost;
import com.example.aluraflix.resource.video.VideoRespGet;
import com.example.aluraflix.spec.VideoSpec;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class VideoService implements VideoSpec {

    private final VideoRepository repository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public VideoService(VideoRepository repository, CategoryRepository categoryRepository) {
        this.repository = repository;
        this.categoryRepository = categoryRepository;
    }

    public GenericResponse<List<VideoRespGet>> findAll() {
        try {
            final var respGetList = repository.findAll()
                    .stream()
                    .map(this::getVideoRespGet)
                    .collect(Collectors.toList());

            return GenericResponse.<List<VideoRespGet>>builder()
                    .isOk(true)
                    .object(respGetList)
                    .build();
        } catch (Exception e) {
            log.debug("Erro ao buscar os videos");
            e.printStackTrace();
            return GenericResponse.<List<VideoRespGet>>builder()
                    .isInternalError(true)
                    .build();
        }
    }

    @Override
    @Transactional
    public GenericResponse<VideoRespGet> create(VideoReqPost request) {
        try {
            var video = new Video();
            video.setDescription(request.getDescription());
            video.setUrl(request.getUrl());
            final var idCategory = request.getIdCategory() == null ? Integer.valueOf(1) : request.getIdCategory();
            video.setCategory(categoryRepository.findById(idCategory).orElseThrow());

            var createdVideo = repository.save(video);
            VideoRespGet resp = getVideoRespGet(createdVideo);

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
                    .object(getVideoRespGet(optionalVideo.get()))
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
                    .object(getVideoRespGet(savedVideo))
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

    private VideoRespGet getVideoRespGet(Video createdVideo) {
        return VideoRespGet.builder()
                .id(createdVideo.getId())
                .description(createdVideo.getDescription())
                .url(createdVideo.getUrl())
                .idCategory(createdVideo.getCategory().getId())
                .titleCategory(createdVideo.getCategory().getTitle())
                .build();
    }


}
