package com.brelinx.edusuite.controller;

import com.brelinx.edusuite.model.Student;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = new ArrayList<>();
        
        // Sample data for testing
        Student student1 = new Student();
        student1.setFirstName("John");
        student1.setLastName("Doe");
        student1.setEmail("john.doe@student.edu");
        student1.setStudentId("STU001");
        student1.setDateOfBirth(LocalDate.of(2000, 5, 15));
        
        Student student2 = new Student();
        student2.setFirstName("Jane");
        student2.setLastName("Smith");
        student2.setEmail("jane.smith@student.edu");
        student2.setStudentId("STU002");
        student2.setDateOfBirth(LocalDate.of(1999, 8, 22));
        
        students.add(student1);
        students.add(student2);
        
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        Student student = new Student();
        student.setFirstName("Sample");
        student.setLastName("Student " + id);
        student.setEmail("student" + id + "@school.edu");
        student.setStudentId("STU" + String.format("%03d", id));
        
        return ResponseEntity.ok(student);
    }

    @PostMapping
    public ResponseEntity<String> createStudent(@RequestBody Student student) {
        return ResponseEntity.ok("Student created successfully: " + student.getFullName());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateStudent(@PathVariable Long id, @RequestBody Student student) {
        return ResponseEntity.ok("Student with ID " + id + " updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        return ResponseEntity.ok("Student with ID " + id + " deleted successfully");
    }
}