package com.ra.model.dao;

import com.ra.model.entity.Product;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductDAOimpl implements ProductDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Product> findAll() {
        Session session = sessionFactory.openSession();
        List<Product> products = new ArrayList<>();
        try {
            products = session.createQuery("from Product", Product.class).list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return products;
    }

    @Override
    public Boolean saveOrUpdate(Product product) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.saveOrUpdate(product);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return false;
    }

    @Override
    public void delete(Integer id) {
        Session session = sessionFactory.openSession();
        Product product = session.get(Product.class, id);
        try {
            session.beginTransaction();
            session.delete(product);
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public Product findById(Integer id) {
        Session session = sessionFactory.openSession();
        try {
            return session.get(Product.class, id);
        }catch (HibernateException e) {
            e.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }
}
