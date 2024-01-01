package com.ra.model.service.category;

import com.ra.model.dto.reponse.CategoryReponse;
import com.ra.model.entity.Category;

import java.util.List;

public interface CategoryService {
    List<CategoryReponse> findAll();

    Category saveOrUpdate(Category category);

    Category findById(Long id);

    void delete(Long id);
}
