package com.brelinx.edusuite.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "grades")
@Getter
@Setter
public class Grade extends BaseEntity {

    private String studentId;
    private String courseId;
    private String teacherId;
    private String schoolId;
    
    private String assignmentName;
    private String assignmentType; // EXAM, QUIZ, HOMEWORK, PROJECT, etc.
    
    private Double score;
    private Double maxScore;
    private String letterGrade; // A, B, C, D, F
    
    private LocalDate dateGraded;
    private String comments;
    
    // Helper methods
    public Double getPercentage() {
        if (maxScore != null && maxScore > 0 && score != null) {
            return (score / maxScore) * 100;
        }
        return 0.0;
    }
    
    public String calculateLetterGrade() {
        Double percentage = getPercentage();
        if (percentage >= 90) return "A";
        else if (percentage >= 80) return "B";
        else if (percentage >= 70) return "C";
        else if (percentage >= 60) return "D";
        else return "F";
    }
}