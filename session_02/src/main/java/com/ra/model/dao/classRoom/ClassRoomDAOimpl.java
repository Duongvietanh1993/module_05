package com.ra.model.dao.classRoom;

import com.ra.model.entity.ClassRoom;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ClassRoomDAOimpl implements ClassRoomDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<ClassRoom> findAll() {
        Session session = sessionFactory.openSession();
        List<ClassRoom> classRooms = new ArrayList<>();
        try {
            classRooms = session.createQuery("from ClassRoom", ClassRoom.class).list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return classRooms;
    }

    @Override
    public Boolean sevaOrUpdate(ClassRoom room) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.saveOrUpdate(room);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return false;
    }

    @Override
    public void delete(Integer id) {
        Session session = sessionFactory.openSession();
        ClassRoom classRoom = findById(id);
        try {
            session.beginTransaction();
            session.delete(classRoom);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }

    @Override
    public ClassRoom findById(Integer id) {
        Session session = sessionFactory.openSession();
        try {
            return session.get(ClassRoom.class, id);
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }
}
