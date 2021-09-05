package com.example.aluraflix.service;

import com.example.aluraflix.model.Video;
import com.example.aluraflix.repository.CategoryRepository;
import com.example.aluraflix.repository.VideoRepository;
import com.example.aluraflix.spec.VideoSpec;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VideoServiceTest {

    private VideoSpec videoService;

    @Mock
    private VideoRepository repository;

    @Mock
    private CategoryRepository categoryRepository;

    @BeforeEach
    void setUp() {
        videoService = new VideoService(repository, categoryRepository, new ModelMapper());
    }

    @Test
    @DisplayName("Verifica se os videos são carregados")
    void findAll() {
        List<Video> videos = new ArrayList<>();
        var video = new Video();
        video.setDescription("teste");
        video.setUrl("http://teste.com.br");
        videos.add(video);
        var videoPage = new PageImpl<>(videos);
        when(repository.findAll(any(Pageable.class))).thenReturn(videoPage);

        var response = videoService.find(null, Pageable.ofSize(10));
        var responseObject = response.getObject();

        assertNotNull(response);
        assertNotNull(responseObject);

        assertTrue(response.isOk());
        assertFalse(response.isNotFound());
        assertFalse(response.isInternalError());


        assertEquals(1, responseObject.getTotalElements());
    }

    @Test
    void create() {
    }

    @Test
    void findById() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}