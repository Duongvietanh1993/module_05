package com.ra.controller;

import com.ra.model.dto.request.ProductRequest;
import com.ra.model.entity.Category;
import com.ra.model.entity.Product;
import com.ra.model.service.category.CategoryService;
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
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAll() {
        List<Product> products = productService.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping("/products")
    public ResponseEntity<ProductRequest> create(@RequestBody ProductRequest product) {
        ProductRequest productNew = productService.saveOrUpdate(product);
        return new ResponseEntity<>(productNew, HttpStatus.CREATED);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<?> getProductById(@PathVariable("id") Long id) {
        ProductRequest product = productService.findById(id);
        if (product != null) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        }
        return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable("id") Long id, @RequestBody ProductRequest productRequest) {
        ProductRequest existingProduct = productService.findById(id);
        if (existingProduct == null) {
            return new ResponseEntity<>(productRequest, HttpStatus.NOT_FOUND);
        }
        existingProduct.setProductName(productRequest.getProductName());
        existingProduct.setPrice(productRequest.getPrice());
        existingProduct.setCategoryId(productRequest.getCategoryId());
        ProductRequest updateProduct = productService.saveOrUpdate(existingProduct);
        if (updateProduct != null) {
            return new ResponseEntity<>(updateProduct, HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Failed to update product",HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
