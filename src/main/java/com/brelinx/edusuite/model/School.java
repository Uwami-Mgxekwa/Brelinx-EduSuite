package com.brelinx.edusuite.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "schools")
@Getter
@Setter
public class School extends BaseEntity {

    @Indexed(unique = true)
    private String name;

    private String logoUrl;

    private String address;

    private String contact;

    @DBRef
    private List<Student> students = new ArrayList<>();

    @DBRef
    private List<Teacher> teachers = new ArrayList<>();

    @DBRef
    private List<Course> courses = new ArrayList<>();

    // Helper methods for relationships
    public void addStudent(Student student) {
        if (students == null) {
            students = new ArrayList<>();
        }
        students.add(student);
        student.setSchoolId(this.getId());
    }

    public void removeStudent(Student student) {
        if (students != null) {
            students.remove(student);
            student.setSchoolId(null);
        }
    }

    public void addTeacher(Teacher teacher) {
        if (teachers == null) {
            teachers = new ArrayList<>();
        }
        teachers.add(teacher);
        teacher.setSchoolId(this.getId());
    }

    public void removeTeacher(Teacher teacher) {
        if (teachers != null) {
            teachers.remove(teacher);
            teacher.setSchoolId(null);
        }
    }

    public void addCourse(Course course) {
        if (courses == null) {
            courses = new ArrayList<>();
        }
        courses.add(course);
        course.setSchoolId(this.getId());
    }

    public void removeCourse(Course course) {
        if (courses != null) {
            courses.remove(course);
            course.setSchoolId(null);
        }
    }
}
