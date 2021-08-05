package com.example.aluraflix.service;

import com.example.aluraflix.model.Video;
import com.example.aluraflix.repository.VideoRepository;
import com.example.aluraflix.spec.VideoSpec;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

class VideoServiceTest {

    private VideoSpec videoService;

    @Mock
    private VideoRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        videoService = new VideoService(repository, null, null);
    }

    @Test
    @DisplayName("Verifica se os videos são carregados")
    void findAll() {
        List<Video> videos = new ArrayList<>();
        var video = new Video();
        video.setDescription("teste");
        video.setUrl("http://teste.com.br");
        videos.add(video);
        when(repository.findAll()).thenReturn(videos);

//        Assertions.assertEquals(1, foundVideos.size());
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