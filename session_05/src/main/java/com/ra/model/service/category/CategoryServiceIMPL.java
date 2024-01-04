package com.ra.model.service.category;

import com.ra.model.dto.reponse.CategoryReponse;
import com.ra.model.entity.Category;
import com.ra.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceIMPL implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Page<CategoryReponse> findAll(Pageable pageable) {
        Page<Category> categoryPage = categoryRepository.findAll(pageable);

        return categoryPage.map(category -> new CategoryReponse(category));
    }

    @Override
    public Category saveOrUpdate(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category findById(Long id) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        return categoryOptional.orElse(null);
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Page<CategoryReponse> searchByName(Pageable pageable, String name) {
        Page<Category> categoryPage = categoryRepository.findByCategoryNameContainingIgnoreCase(pageable, name);
        return categoryPage.map(CategoryReponse::new);
    }





}
