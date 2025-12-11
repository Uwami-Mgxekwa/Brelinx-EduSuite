package com.brelinx.edusuite.controller;

import com.brelinx.edusuite.model.Teacher;
import com.brelinx.edusuite.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {

    @Autowired
    private TeacherRepository teacherRepository;

    @GetMapping
    public ResponseEntity<List<Teacher>> getAllTeachers() {
        List<Teacher> teachers = teacherRepository.findAll();
        
        // If no teachers exist, create some sample data
        if (teachers.isEmpty()) {
            Teacher teacher1 = new Teacher();
            teacher1.setFirstName("Dr. Sarah");
            teacher1.setLastName("Johnson");
            teacher1.setEmail("sarah.johnson@brelinxacademy.com");
            teacher1.setEmployeeId("EMP001");
            teacher1.setDepartment("Mathematics");
            teacher1.setHireDate(LocalDate.of(2020, 8, 15));
            teacher1.setPhoneNumber("+1-555-0101");
            
            Teacher teacher2 = new Teacher();
            teacher2.setFirstName("Prof. Michael");
            teacher2.setLastName("Chen");
            teacher2.setEmail("michael.chen@brelinxacademy.com");
            teacher2.setEmployeeId("EMP002");
            teacher2.setDepartment("Computer Science");
            teacher2.setHireDate(LocalDate.of(2019, 1, 10));
            teacher2.setPhoneNumber("+1-555-0102");
            
            teacherRepository.save(teacher1);
            teacherRepository.save(teacher2);
            
            teachers = teacherRepository.findAll();
        }
        
        return ResponseEntity.ok(teachers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable String id) {
        Optional<Teacher> teacher = teacherRepository.findById(id);
        return teacher.map(ResponseEntity::ok)
                     .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/school/{schoolId}")
    public ResponseEntity<List<Teacher>> getTeachersBySchool(@PathVariable String schoolId) {
        List<Teacher> teachers = teacherRepository.findBySchoolId(schoolId);
        return ResponseEntity.ok(teachers);
    }

    @GetMapping("/department/{department}")
    public ResponseEntity<List<Teacher>> getTeachersByDepartment(@PathVariable String department) {
        List<Teacher> teachers = teacherRepository.findByDepartment(department);
        return ResponseEntity.ok(teachers);
    }

    @PostMapping
    public ResponseEntity<Teacher> createTeacher(@RequestBody Teacher teacher) {
        if (teacherRepository.existsByEmail(teacher.getEmail()) || 
            teacherRepository.existsByEmployeeId(teacher.getEmployeeId())) {
            return ResponseEntity.badRequest().build();
        }
        Teacher savedTeacher = teacherRepository.save(teacher);
        return ResponseEntity.ok(savedTeacher);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Teacher> updateTeacher(@PathVariable String id, @RequestBody Teacher teacher) {
        if (!teacherRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        teacher.setId(id);
        Teacher updatedTeacher = teacherRepository.save(teacher);
        return ResponseEntity.ok(updatedTeacher);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable String id) {
        if (!teacherRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        teacherRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}