package com.ra.model.service;

import com.ra.model.entity.Category;
import com.ra.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceIMPL implements CategoryService{
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
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
}
