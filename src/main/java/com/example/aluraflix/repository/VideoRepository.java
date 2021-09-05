package com.example.aluraflix.repository;

import com.example.aluraflix.model.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoRepository extends JpaRepository<Video, Integer> {

    List<Video> findByCategoryId(Integer id);

    Page<Video> findByDescriptionContainingIgnoreCase(String description, Pageable pageable);

}
