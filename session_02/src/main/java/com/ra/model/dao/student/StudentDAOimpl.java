package com.ra.model.dao.student;

import com.ra.model.entity.Student;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class StudentDAOimpl implements StudentDAO{
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public List<Student> findAll() {
        Session session = sessionFactory.openSession();
        List<Student> students = new ArrayList<>();
        try {
            students = session.createQuery("from Student", Student.class).list();
        }catch (HibernateException e) {
            e.printStackTrace();
        }finally {
            session.close();
        }
        return students;
    }

    @Override
    public Boolean sevaOrUpdate(Student student) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.saveOrUpdate(student);
            session.getTransaction().commit();
            return true;
        }catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
        return false;
    }

    @Override
    public void delete(Integer id) {
        Session session = sessionFactory.openSession();
        Student student = findById(id);
        try {
            session.beginTransaction();
            session.delete(student);
            session.getTransaction().commit();
        }catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }

    @Override
    public Student findById(Integer id) {
        Session session = sessionFactory.openSession();
        try {
            return session.get(Student.class,id);
        }catch (HibernateException e){
            e.printStackTrace();
        }
        return null;
    }
}
