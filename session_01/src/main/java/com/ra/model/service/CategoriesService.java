package com.ra.model.service;

import com.ra.model.entity.Categories;

import java.util.List;

public interface CategoriesService {
    List<Categories> findAll();
    Boolean saveOrUpdate(Categories categories);
    void delete(Integer id);
    Categories findById(Integer id);
}
