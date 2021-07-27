package com.example.aluraflix.spec;

import java.util.List;

public interface CrudSpec<T, V> {

    List<T> findAll();

    T create(V request);

    T findById(Integer id);

    T update(Integer id, V request);

    boolean delete(Integer id);

}
