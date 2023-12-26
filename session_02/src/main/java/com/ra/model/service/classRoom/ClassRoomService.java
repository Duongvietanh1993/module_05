package com.ra.model.service.classRoom;

import com.ra.model.entity.ClassRoom;

import java.util.List;

public interface ClassRoomService {
    List<ClassRoom> findAll();
    Boolean sevaOrUpdate(ClassRoom room);
    void delete (Integer id);
    ClassRoom findById(Integer id);
}
