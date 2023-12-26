package com.ra.model.service.classRoom;

import com.ra.model.dao.classRoom.ClassRoomDAO;
import com.ra.model.entity.ClassRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassRoomServiceIMPL implements ClassRoomService {
    @Autowired
    private ClassRoomDAO classRoomDAO;

    @Override
    public List<ClassRoom> findAll() {
        return classRoomDAO.findAll();
    }

    @Override
    public Boolean sevaOrUpdate(ClassRoom room) {
        return classRoomDAO.sevaOrUpdate(room);
    }

    @Override
    public void delete(Integer id) {
        classRoomDAO.delete(id);
    }

    @Override
    public ClassRoom findById(Integer id) {
        return classRoomDAO.findById(id);
    }
}
