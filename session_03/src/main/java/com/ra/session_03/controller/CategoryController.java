package com.ra.session_03.controller;

import com.ra.session_03.model.entity.Category;
import com.ra.session_03.model.service.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/category")
    public String category(Model model) {
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        return "category/index";
    }

    @GetMapping("/create-category")
    public String createCategory(Model model) {
        Category category = new Category();
        model.addAttribute("category", category);
        return "category/create-category";
    }

    @PostMapping("/create-category")
    public String handleCreateCategory(@ModelAttribute("category") Category category) {
        if (categoryService.saveOrUpdate(category) != null) {
            return "redirect:/category";
        }
        return "category/create-category";
    }

    @GetMapping("/update-category/{id}")
    public String update(@PathVariable("id") Long id, Model model) {
        Category category = categoryService.findById(id);
        model.addAttribute("category", category);
        return "category/edit-category";
    }
    @PostMapping("/update-category")
    public String handleUpdateCategory(@ModelAttribute("category") Category category) {
        if (categoryService.saveOrUpdate(category) != null) {
            return "redirect:/category";
        }
        return "category/edit-category";
    }
}
