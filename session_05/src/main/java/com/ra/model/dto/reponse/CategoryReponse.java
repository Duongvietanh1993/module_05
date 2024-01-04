package com.ra.model.dto.reponse;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ra.model.entity.Category;
import com.ra.model.entity.Product;
import jakarta.persistence.*;

import java.util.List;

public class CategoryReponse {
    private Long id;
    private String categoryName;
    private boolean status = true;
    private List<Product> products;

    public CategoryReponse() {
    }
    public CategoryReponse(Category category) {
        this.id = category.getId();
        this.categoryName = category.getCategoryName();
        this.status = category.isStatus();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
