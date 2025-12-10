package com.brelinx.edusuite.controller;

import com.brelinx.edusuite.model.School;
import com.brelinx.edusuite.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/schools")
public class SchoolController {

    @Autowired
    private SchoolRepository schoolRepository;

    @GetMapping
    public ResponseEntity<List<School>> getAllSchools() {
        List<School> schools = schoolRepository.findAll();
        
        // If no schools exist, create some sample data
        if (schools.isEmpty()) {
            School school1 = new School();
            school1.setName("Brelinx Academy");
            school1.setAddress("123 Education Street, Learning City");
            school1.setContact("info@brelinxacademy.com");
            
            School school2 = new School();
            school2.setName("Tech High School");
            school2.setAddress("456 Innovation Ave, Tech Town");
            school2.setContact("contact@techhigh.edu");
            
            schoolRepository.save(school1);
            schoolRepository.save(school2);
            
            schools = schoolRepository.findAll();
        }
        
        return ResponseEntity.ok(schools);
    }

    @GetMapping("/{id}")
    public ResponseEntity<School> getSchoolById(@PathVariable String id) {
        Optional<School> school = schoolRepository.findById(id);
        return school.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<School> createSchool(@RequestBody School school) {
        if (schoolRepository.existsByName(school.getName())) {
            return ResponseEntity.badRequest().build();
        }
        School savedSchool = schoolRepository.save(school);
        return ResponseEntity.ok(savedSchool);
    }

    @PutMapping("/{id}")
    public ResponseEntity<School> updateSchool(@PathVariable String id, @RequestBody School school) {
        if (!schoolRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        school.setId(id);
        School updatedSchool = schoolRepository.save(school);
        return ResponseEntity.ok(updatedSchool);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchool(@PathVariable String id) {
        if (!schoolRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        schoolRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}