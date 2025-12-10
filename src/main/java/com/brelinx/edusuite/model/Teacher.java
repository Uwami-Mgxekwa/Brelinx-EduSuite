package com.brelinx.edusuite.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "teachers")
@Getter
@Setter
public class Teacher extends BaseEntity {

    private String firstName;

    private String lastName;

    @Indexed(unique = true)
    private String email;

    @Indexed(unique = true)
    private String employeeId;

    private String phoneNumber;

    private String address;

    private String department;

    private LocalDate hireDate;

    private String schoolId;

    private List<String> courseIds = new ArrayList<>();

    // Helper methods
    public void addCourseId(String courseId) {
        if (courseIds == null) {
            courseIds = new ArrayList<>();
        }
        if (!courseIds.contains(courseId)) {
            courseIds.add(courseId);
        }
    }

    public void removeCourseId(String courseId) {
        if (courseIds != null) {
            courseIds.remove(courseId);
        }
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }
}