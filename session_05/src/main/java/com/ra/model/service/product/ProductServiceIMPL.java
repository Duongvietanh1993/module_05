package com.ra.model.service.product;

import com.ra.model.dto.request.ProductRequest;
import com.ra.model.entity.Category;
import com.ra.model.entity.Product;
import com.ra.model.service.category.CategoryService;
import com.ra.repository.CategoryRepository;
import com.ra.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceIMPL implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryService categoryService;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public ProductRequest saveOrUpdate(ProductRequest productRequest) {
        //đối tượng từ dto sang entity
        Product product = new Product();
        product.setProductName(productRequest.getProductName());
        product.setPrice(productRequest.getPrice());
        Category category = categoryService.findById(productRequest.getCategoryId());
        product.setCategory(category);
        productRepository.save(product);
        productRequest.setId(product.getId());
        return productRequest;
    }

    @Override
    public ProductRequest findById(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            ProductRequest productRequest = new ProductRequest();
            productRequest.setId(product.getId());
            productRequest.setProductName(product.getProductName());
            productRequest.setPrice(product.getPrice());
            productRequest.setCategoryId(product.getCategory().getId());
            return productRequest;
        } else {
            throw new EntityNotFoundException("Product with ID" + id + "not found");
        }
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
