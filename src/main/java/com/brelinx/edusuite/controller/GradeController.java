package com.brelinx.edusuite.controller;

import com.brelinx.edusuite.model.Grade;
import com.brelinx.edusuite.repository.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/grades")
public class GradeController {

    @Autowired
    private GradeRepository gradeRepository;

    @GetMapping
    public ResponseEntity<List<Grade>> getAllGrades() {
        List<Grade> grades = gradeRepository.findAll();
        
        // If no grades exist, create some sample data
        if (grades.isEmpty()) {
            Grade grade1 = new Grade();
            grade1.setAssignmentName("Midterm Exam");
            grade1.setAssignmentType("EXAM");
            grade1.setScore(85.0);
            grade1.setMaxScore(100.0);
            grade1.setLetterGrade("B");
            grade1.setDateGraded(LocalDate.now().minusDays(7));
            grade1.setComments("Good understanding of concepts");
            
            Grade grade2 = new Grade();
            grade2.setAssignmentName("Programming Project 1");
            grade2.setAssignmentType("PROJECT");
            grade2.setScore(92.0);
            grade2.setMaxScore(100.0);
            grade2.setLetterGrade("A");
            grade2.setDateGraded(LocalDate.now().minusDays(3));
            grade2.setComments("Excellent work, well structured code");
            
            gradeRepository.save(grade1);
            gradeRepository.save(grade2);
            
            grades = gradeRepository.findAll();
        }
        
        return ResponseEntity.ok(grades);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Grade> getGradeById(@PathVariable String id) {
        Optional<Grade> grade = gradeRepository.findById(id);
        return grade.map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Grade>> getGradesByStudent(@PathVariable String studentId) {
        List<Grade> grades = gradeRepository.findByStudentId(studentId);
        return ResponseEntity.ok(grades);
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<Grade>> getGradesByCourse(@PathVariable String courseId) {
        List<Grade> grades = gradeRepository.findByCourseId(courseId);
        return ResponseEntity.ok(grades);
    }

    @GetMapping("/teacher/{teacherId}")
    public ResponseEntity<List<Grade>> getGradesByTeacher(@PathVariable String teacherId) {
        List<Grade> grades = gradeRepository.findByTeacherId(teacherId);
        return ResponseEntity.ok(grades);
    }

    @GetMapping("/student/{studentId}/course/{courseId}")
    public ResponseEntity<List<Grade>> getGradesByStudentAndCourse(
            @PathVariable String studentId, @PathVariable String courseId) {
        List<Grade> grades = gradeRepository.findByStudentIdAndCourseId(studentId, courseId);
        return ResponseEntity.ok(grades);
    }

    @PostMapping
    public ResponseEntity<Grade> createGrade(@RequestBody Grade grade) {
        // Auto-calculate letter grade if not provided
        if (grade.getLetterGrade() == null || grade.getLetterGrade().isEmpty()) {
            grade.setLetterGrade(grade.calculateLetterGrade());
        }
        
        if (grade.getDateGraded() == null) {
            grade.setDateGraded(LocalDate.now());
        }
        
        Grade savedGrade = gradeRepository.save(grade);
        return ResponseEntity.ok(savedGrade);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Grade> updateGrade(@PathVariable String id, @RequestBody Grade grade) {
        if (!gradeRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        grade.setId(id);
        
        // Auto-calculate letter grade if not provided
        if (grade.getLetterGrade() == null || grade.getLetterGrade().isEmpty()) {
            grade.setLetterGrade(grade.calculateLetterGrade());
        }
        
        Grade updatedGrade = gradeRepository.save(grade);
        return ResponseEntity.ok(updatedGrade);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGrade(@PathVariable String id) {
        if (!gradeRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        gradeRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}