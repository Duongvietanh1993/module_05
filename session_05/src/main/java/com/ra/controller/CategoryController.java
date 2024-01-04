package com.ra.controller;

import com.ra.model.dto.reponse.CategoryReponse;
import com.ra.model.entity.Category;
import com.ra.model.service.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/v1/api/search")
    public ResponseEntity<Page<CategoryReponse>> search(@RequestParam(name = "keyword")String keyword,
                                                        @RequestParam(defaultValue = "5",name = "limit")int limit,
                                                        @RequestParam(defaultValue = "0",name = "page")int page,
                                                        @RequestParam(defaultValue = "id", name = "sort") String sort,
                                                        @RequestParam(defaultValue = "asc", name = "order") String order){
        Pageable pageable;
        if (order.equalsIgnoreCase("desc")) {
            pageable = PageRequest.of(page, limit, Sort.by(sort).descending());
        } else {
            pageable = PageRequest.of(page, limit, Sort.by(sort).ascending());
        }
        Page<CategoryReponse> categoryReponses= categoryService.searchByName(pageable,keyword);
        return new ResponseEntity<>(categoryReponses,HttpStatus.OK);
    }
    @GetMapping("/categories")
    public ResponseEntity<Page<CategoryReponse>> getAll(
            @RequestParam(defaultValue = "5", name = "limit") int limit,
            @RequestParam(defaultValue = "0", name = "page") int page,
            @RequestParam(defaultValue = "id", name = "sort") String sort,
            @RequestParam(defaultValue = "asc", name = "order") String order) {

        Pageable pageable;
        if (order.equalsIgnoreCase("desc")) {
            pageable = PageRequest.of(page, limit, Sort.by(sort).descending());
        } else {
            pageable = PageRequest.of(page, limit, Sort.by(sort).ascending());
        }

        Page<CategoryReponse> categoryPage = categoryService.findAll(pageable);

        return new ResponseEntity<>(categoryPage, HttpStatus.OK);
    }

    @PostMapping("/categories")
    public ResponseEntity<Category> create(@RequestBody Category category) {
        Category cat = categoryService.saveOrUpdate(category);
        return new ResponseEntity<>(cat, HttpStatus.CREATED);
    }
    @GetMapping("/categories/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable("id") Long id) {
        Category category = categoryService.findById(id);
        if (category != null){
            return new ResponseEntity<>(category, HttpStatus.OK);
        }
        return new ResponseEntity<>("Not Found",HttpStatus.NOT_FOUND);
    }

    @PutMapping("/categories/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id,@RequestBody Category category){
        Category existingCategory = categoryService.findById(id);
        if (existingCategory == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        existingCategory.setCategoryName(category.getCategoryName());
        existingCategory.setStatus(category.isStatus());
        Category categoryUpdate = categoryService.saveOrUpdate(existingCategory);
        return new ResponseEntity<>(categoryUpdate, HttpStatus.OK);
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        if (categoryService.findById(id) != null) {
            categoryService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>("Not Found",HttpStatus.NOT_FOUND);
    }
}
