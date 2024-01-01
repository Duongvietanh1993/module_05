package com.ra.model.service;

import com.ra.model.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();

    Category saveOrUpdate(Category category);

    Category findById(Long id);

    void delete(Long id);
}
