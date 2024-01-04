package com.ra.model.service.product;

import com.ra.model.dto.request.ProductRequest;
import com.ra.model.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();

    ProductRequest saveOrUpdate(ProductRequest product);

    ProductRequest findById(Long id);

    void delete(Long id);
}
