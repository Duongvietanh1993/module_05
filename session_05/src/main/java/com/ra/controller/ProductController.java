package com.ra.controller;

import com.ra.model.dto.request.ProductRequest;
import com.ra.model.entity.Product;
import com.ra.model.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAll() {
        List<Product> products = productService.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
    @PostMapping("/products")
    public ResponseEntity<Product> create(@RequestBody ProductRequest product){
        Product productNew = productService.saveOrUpdate(product);
        return new ResponseEntity<>(productNew, HttpStatus.CREATED);
    }
}
