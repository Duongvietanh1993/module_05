package com.ra.model.service.product;

import com.ra.model.dto.request.ProductRequest;
import com.ra.model.entity.Category;
import com.ra.model.entity.Product;
import com.ra.model.service.category.CategoryService;
import com.ra.repository.CategoryRepository;
import com.ra.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceIMPL implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product saveOrUpdate(ProductRequest productRequest) {
        //đối tượng từ dto sang entity
        Product product = new Product();
        product.setProductName(productRequest.getProductName());
        product.setPrice(productRequest.getPrice());
        Optional<Category> categoryOptional = categoryRepository.findById(productRequest.getCategoryId());
        product.setCategory(categoryOptional.orElse(null));
        return productRepository.save(product);
    }

    @Override
    public Product findById(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        return productOptional.orElse(null);
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
