package com.brelinx.edusuite.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "schools")
@Getter
@Setter
public class School extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String name;

    @Column(name = "logo_url")
    private String logoUrl;

    private String address;

    private String contact;

    @OneToMany(mappedBy = "school", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Student> students = new HashSet<>();

    @OneToMany(mappedBy = "school", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Teacher> teachers = new HashSet<>();

    @OneToMany(mappedBy = "school", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Course> courses = new HashSet<>();

    // Helper methods for bidirectional relationships
    public void addStudent(Student student) {
        students.add(student);
        student.setSchool(this);
    }

    public void removeStudent(Student student) {
        students.remove(student);
        student.setSchool(null);
    }

    public void addTeacher(Teacher teacher) {
        teachers.add(teacher);
        teacher.setSchool(this);
    }

    public void removeTeacher(Teacher teacher) {
        teachers.remove(teacher);
        teacher.setSchool(null);
    }

    public void addCourse(Course course) {
        courses.add(course);
        course.setSchool(this);
    }

    public void removeCourse(Course course) {
        courses.remove(course);
        course.setSchool(null);
    }
}
