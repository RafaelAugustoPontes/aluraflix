package com.example.aluraflix.repository;

import com.example.aluraflix.model.Category;
import com.example.aluraflix.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {


}
