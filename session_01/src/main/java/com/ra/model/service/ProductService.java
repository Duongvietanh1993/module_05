package com.ra.model.service;

import com.ra.model.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();
    Boolean saveOrUpdate(Product product);
    void delete(Integer id);
    Product findById(Integer id);
}
