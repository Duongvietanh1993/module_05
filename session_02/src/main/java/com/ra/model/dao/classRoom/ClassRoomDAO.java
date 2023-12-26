package com.ra.model.dao.classRoom;

import com.ra.model.entity.ClassRoom;

import java.util.List;

public interface ClassRoomDAO {
    List<ClassRoom> findAll();
    Boolean sevaOrUpdate(ClassRoom room);
    void delete (Integer id);
    ClassRoom findById(Integer id);
}
