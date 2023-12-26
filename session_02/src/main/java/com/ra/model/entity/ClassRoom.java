package com.ra.model.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "class_room")
public class ClassRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int classId;
    @Column(name = "name")
    private String className;
    @Column(name = "status")
    private Boolean classStatus;
    @OneToMany(mappedBy = "class_room", cascade=CascadeType.REMOVE)
    private List<Student> students;

    public ClassRoom() {
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Boolean getClassStatus() {
        return classStatus;
    }

    public void setClassStatus(Boolean classStatus) {
        this.classStatus = classStatus;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
