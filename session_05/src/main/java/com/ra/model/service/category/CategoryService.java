package com.ra.model.service.category;

import com.ra.model.dto.reponse.CategoryReponse;
import com.ra.model.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {
    Page<CategoryReponse> findAll(Pageable pageable);

    Category saveOrUpdate(Category category);

    Category findById(Long id);

    void delete(Long id);

    Page<CategoryReponse> searchByName(Pageable pageable,String name);

}
