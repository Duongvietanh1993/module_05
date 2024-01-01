package com.ra.model.service.category;

import com.ra.model.dto.reponse.CategoryReponse;
import com.ra.model.entity.Category;
import com.ra.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceIMPL implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<CategoryReponse> findAll() {
        List<Category> categoryList = categoryRepository.findAll();
        List<CategoryReponse> categoryReponseList = new ArrayList<>();
        for (Category category : categoryList) {
            CategoryReponse categoryReponse = new CategoryReponse();
            categoryReponse.setId(category.getId());
            categoryReponse.setCategoryName(category.getCategoryName());
            categoryReponse.setStatus(category.isStatus());
            categoryReponse.setProducts(category.getProducts());
            categoryReponseList.add(categoryReponse);
        }
        return categoryReponseList;
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
