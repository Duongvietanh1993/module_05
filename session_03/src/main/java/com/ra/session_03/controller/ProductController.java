package com.ra.session_03.controller;

import com.ra.session_03.model.entity.Category;
import com.ra.session_03.model.entity.Product;
import com.ra.session_03.model.service.category.CategoryService;
import com.ra.session_03.model.service.product.ProductService;
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
    private CategoryService categoryService;
    @GetMapping("/product")
    public String product(Model model){
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return "product/index";
    }

    @GetMapping("/create-product")
    public String createProduct(Model model){
        Product product = new Product();
        List<Category> categories = categoryService.findAll();
        model.addAttribute("product", product);
        model.addAttribute("categories", categories);
        return "product/create-product";
    }
    @PostMapping("/create-product")
    public String handleCreateProduct(@ModelAttribute("product") Product product){
        if (productService.saveOrUpdate(product)!=null){
            return "redirect:/product";
        }
        return "product/create-product";
    }
    @GetMapping("/update-product/{id}")
    public String updateProduct(@PathVariable("id") Long id,Model model){
        Product product = productService.findById(id);
        List<Category> categories = categoryService.findAll();
        model.addAttribute("product",product);
        model.addAttribute("categories", categories);
        return "product/edit-product";
    }
    @PostMapping("/update-product")
    public String handleUpdateProduct(@ModelAttribute("product") Product product){
        if(productService.saveOrUpdate(product)!=null){
            return "redirect:/product";
        }
        return "product/edit-product";
    }
}
