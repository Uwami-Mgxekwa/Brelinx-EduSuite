package com.brelinx.edusuite.controller;

import com.brelinx.edusuite.model.School;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/schools")
public class SchoolController {

    @GetMapping
    public ResponseEntity<List<School>> getAllSchools() {
        List<School> schools = new ArrayList<>();
        
        // Sample data for testing
        School school1 = new School();
        school1.setName("Brelinx Academy");
        school1.setAddress("123 Education Street, Learning City");
        school1.setContact("info@brelinxacademy.com");
        
        School school2 = new School();
        school2.setName("Tech High School");
        school2.setAddress("456 Innovation Ave, Tech Town");
        school2.setContact("contact@techhigh.edu");
        
        schools.add(school1);
        schools.add(school2);
        
        return ResponseEntity.ok(schools);
    }

    @GetMapping("/{id}")
    public ResponseEntity<School> getSchoolById(@PathVariable Long id) {
        School school = new School();
        school.setName("Sample School " + id);
        school.setAddress("Sample Address " + id);
        school.setContact("contact" + id + "@school.edu");
        
        return ResponseEntity.ok(school);
    }

    @PostMapping
    public ResponseEntity<String> createSchool(@RequestBody School school) {
        return ResponseEntity.ok("School created successfully: " + school.getName());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateSchool(@PathVariable Long id, @RequestBody School school) {
        return ResponseEntity.ok("School with ID " + id + " updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSchool(@PathVariable Long id) {
        return ResponseEntity.ok("School with ID " + id + " deleted successfully");
    }
}