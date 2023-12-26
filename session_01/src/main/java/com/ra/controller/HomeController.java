package com.ra.controller;

import com.ra.model.entity.Categories;
import com.ra.model.service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private CategoriesService categoriesService;

    @GetMapping("/")
    public String listCategories(Model model) {
        List<Categories> categoriesList = categoriesService.findAll();
        model.addAttribute("categoriesList", categoriesList);
        return "index";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        Categories category = new Categories();
        model.addAttribute("category", category);
        return "create";
    }

    @PostMapping("/create")
    public String createCategory(@ModelAttribute("category") Categories category) {
        categoriesService.saveOrUpdate(category);
        return "redirect:/";
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Categories category = categoriesService.findById(id);
        model.addAttribute("category", category);
        return "edit";
    }

    @PostMapping("/update/{id}")
    public String updateCategory(@ModelAttribute("category") Categories category) {
        categoriesService.saveOrUpdate(category);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable("id") Integer id) {
        categoriesService.delete(id);
        return "redirect:/";
    }
}
