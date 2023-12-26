package com.ra.model.dao;

import com.ra.model.entity.Categories;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CategoryDAOimpl implements CategoriesDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Categories> findAll() {
        Session session = sessionFactory.openSession();
        List<Categories> list = new ArrayList<>();
        try {
            list = session.createQuery("from Categories", Categories.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return list;
    }

    @Override
    public Boolean saveOrUpdate(Categories categories) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(categories);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(Integer id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Categories categories = session.get(Categories.class, id);
            if (categories != null) {
                session.delete(categories);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }

            e.printStackTrace();
        } finally {
            session.close();
        }
    }


    @Override
    public Categories findById(Integer id) {
        Session session = sessionFactory.openSession();
        try {
            return session.get(Categories.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }
}
