package com.example.aluraflix.repository;

import com.example.aluraflix.model.Video;
import com.example.aluraflix.service.BaseIntegrationTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class VideoRepositoryTest extends BaseIntegrationTest {

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @BeforeEach
    public void setUp() {

    }

    @Test
    void save() {
        var video = new Video();
        video.setDescription("Video de test");
        video.setUrl("http://teste.com.br");
        video.setCategory(categoryRepository.findById(1).orElseThrow());
        var newVideo = videoRepository.save(video);
        var videoByID = videoRepository.findById(newVideo.getId());
        Assertions.assertNotNull(videoByID);
        Assertions.assertEquals(newVideo.getId(), videoByID.orElseThrow().getId());
    }

    @Test
    void save_videoWithoutCategory() {
        var video = new Video();
        video.setDescription("Video de test");
        video.setUrl("http://teste.com.br");

        var assertThrows = Assertions.assertThrows(Exception.class, () -> videoRepository.save(video), "Should throw constraint exception");
        Assertions.assertNotNull(assertThrows);
    }


    @Test
    void findByCategoryId() {
        var video = new Video();
        video.setDescription("Video de test");
        video.setUrl("http://teste.com.br");
        video.setCategory(categoryRepository.findById(1).orElseThrow());
        var newVideo = videoRepository.save(video);
        var videoByID = videoRepository.findById(newVideo.getId());
        Assertions.assertNotNull(videoByID);
        Assertions.assertEquals(newVideo.getId(), videoByID.orElseThrow().getId());
    }

    @AfterEach
    void tearDown() {
        videoRepository.deleteAll();
    }

}