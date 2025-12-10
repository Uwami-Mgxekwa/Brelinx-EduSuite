package com.brelinx.edusuite.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "teachers")
@Getter
@Setter
public class Teacher extends BaseEntity {

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String employeeId;

    private String phoneNumber;

    private String address;

    private String department;

    private LocalDate hireDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id")
    private School school;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Course> courses = new HashSet<>();

    // Helper methods for bidirectional relationships
    public void addCourse(Course course) {
        courses.add(course);
        course.setTeacher(this);
    }

    public void removeCourse(Course course) {
        courses.remove(course);
        course.setTeacher(null);
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }
}