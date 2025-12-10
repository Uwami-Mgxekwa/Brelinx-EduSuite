package com.brelinx.edusuite.controller;

import com.brelinx.edusuite.model.Student;
import com.brelinx.edusuite.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        
        // If no students exist, create some sample data
        if (students.isEmpty()) {
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
            
            studentRepository.save(student1);
            studentRepository.save(student2);
            
            students = studentRepository.findAll();
        }
        
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable String id) {
        @SuppressWarnings("null")
        Optional<Student> student = studentRepository.findById(id);
        return student.map(ResponseEntity::ok)
                     .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        if (studentRepository.existsByEmail(student.getEmail()) || 
            studentRepository.existsByStudentId(student.getStudentId())) {
            return ResponseEntity.badRequest().build();
        }
        Student savedStudent = studentRepository.save(student);
        return ResponseEntity.ok(savedStudent);
    }

    @SuppressWarnings("null")
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable String id, @RequestBody Student student) {
        if (!studentRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        student.setId(id);
        Student updatedStudent = studentRepository.save(student);
        return ResponseEntity.ok(updatedStudent);
    }

    @SuppressWarnings("null")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable String id) {
        if (!studentRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        studentRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}