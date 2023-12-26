package com.ra.model.service;

import com.ra.model.dao.CategoriesDAO;
import com.ra.model.entity.Categories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoriesServiceIMPL implements CategoriesService{
    @Autowired
    private CategoriesDAO categoriesDAO;
    @Override
    public List<Categories> findAll() {
        return categoriesDAO.findAll();
    }

    @Override
    public Boolean saveOrUpdate(Categories categories) {
        return categoriesDAO.saveOrUpdate(categories);
    }

    @Override
    public void delete(Integer id) {
categoriesDAO.delete(id);
    }

    @Override
    public Categories findById(Integer id) {
        return categoriesDAO.findById(id);
    }
}
