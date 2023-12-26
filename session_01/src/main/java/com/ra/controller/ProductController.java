package com.ra.controller;

import com.ra.model.entity.Categories;
import com.ra.model.entity.Product;
import com.ra.model.service.CategoriesService;
import com.ra.model.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoriesService categoriesService;
    @GetMapping("/products")
    public String product(Model model){
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return "product";
    }
    @GetMapping("/add-product")
    public String addProduct(Model model){
        Product product = new Product();
        List<Categories> categories = categoriesService.findAll();
        model.addAttribute("product", product);
        model.addAttribute("categories",categories);
        return "create-product";
    }
    @PostMapping("/add-product")
    public String createProduct(@ModelAttribute("product") Product product){
        if (productService.saveOrUpdate(product)){
            return "redirect:/products";
        }
        return "create-product";
    }

    @GetMapping("/edit-product/{id}")
    public String editProduct(@PathVariable("id")Integer id, Model model){
        Product product = productService.findById(id);
        List<Categories> categories = categoriesService.findAll();
        model.addAttribute("product", product);
        model.addAttribute("categories",categories);
        return "edit-product";
    }
    @PostMapping("/edit-product")
    public String handleProductUpdate(@ModelAttribute("product")Product product){
        productService.saveOrUpdate(product);
        return "redirect:/products";
    }
}
