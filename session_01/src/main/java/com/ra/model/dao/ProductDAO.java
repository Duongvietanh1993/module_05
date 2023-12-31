package com.ra.model.dao;

import com.ra.model.entity.Product;

import java.util.List;

public interface ProductDAO {
    List<Product> findAll();
    Boolean saveOrUpdate(Product product);
    void delete(Integer id);
    Product findById(Integer id);
}
