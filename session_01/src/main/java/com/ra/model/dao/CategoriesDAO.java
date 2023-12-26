package com.ra.model.dao;

import com.ra.model.entity.Categories;

import java.util.List;

public interface CategoriesDAO {
    List<Categories> findAll();
    Boolean saveOrUpdate(Categories categories);
    void delete(Integer id);
    Categories findById(Integer id);
}
