package com.brelinx.edusuite.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "courses")
@Getter
@Setter
public class Course extends BaseEntity {

    private String name;

    @Indexed(unique = true)
    private String courseCode;

    private String description;

    private Integer credits;

    private LocalDate startDate;

    private LocalDate endDate;

    private String schoolId;

    private String teacherId;

    private List<String> studentIds = new ArrayList<>();

    // Helper methods
    public void addStudentId(String studentId) {
        if (studentIds == null) {
            studentIds = new ArrayList<>();
        }
        if (!studentIds.contains(studentId)) {
            studentIds.add(studentId);
        }
    }

    public void removeStudentId(String studentId) {
        if (studentIds != null) {
            studentIds.remove(studentId);
        }
    }
}