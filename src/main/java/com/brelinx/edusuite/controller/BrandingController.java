package com.brelinx.edusuite.controller;

import com.brelinx.edusuite.model.SchoolBranding;
import com.brelinx.edusuite.repository.SchoolBrandingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/branding")
public class BrandingController {

    @Autowired
    private SchoolBrandingRepository brandingRepository;

    @GetMapping
    public ResponseEntity<List<SchoolBranding>> getAllBranding() {
        List<SchoolBranding> branding = brandingRepository.findAll();
        
        // If no branding exists, create default branding
        if (branding.isEmpty()) {
            SchoolBranding defaultBranding = new SchoolBranding();
            defaultBranding.setSchoolName("Brelinx EduSuite Demo");
            defaultBranding.setWelcomeMessage("Welcome to Brelinx EduSuite - Your Complete School Management Solution");
            defaultBranding.setContactEmail("info@brelinxedusuite.com");
            defaultBranding.setContactPhone("+1-555-EDUSUITE");
            defaultBranding.setAddress("123 Education Street, Learning City, LC 12345");
            defaultBranding.setWebsite("https://brelinxedusuite.com");
            
            brandingRepository.save(defaultBranding);
            branding = brandingRepository.findAll();
        }
        
        return ResponseEntity.ok(branding);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SchoolBranding> getBrandingById(@PathVariable String id) {
        Optional<SchoolBranding> branding = brandingRepository.findById(id);
        return branding.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/school/{schoolId}")
    public ResponseEntity<SchoolBranding> getBrandingBySchool(@PathVariable String schoolId) {
        Optional<SchoolBranding> branding = brandingRepository.findBySchoolId(schoolId);
        
        if (branding.isEmpty()) {
            // Create default branding for this school
            SchoolBranding defaultBranding = new SchoolBranding();
            defaultBranding.setSchoolId(schoolId);
            defaultBranding.setSchoolName("School " + schoolId);
            SchoolBranding savedBranding = brandingRepository.save(defaultBranding);
            return ResponseEntity.ok(savedBranding);
        }
        
        return ResponseEntity.ok(branding.get());
    }

    @PostMapping
    public ResponseEntity<SchoolBranding> createBranding(@RequestBody SchoolBranding branding) {
        SchoolBranding savedBranding = brandingRepository.save(branding);
        return ResponseEntity.ok(savedBranding);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SchoolBranding> updateBranding(@PathVariable String id, @RequestBody SchoolBranding branding) {
        if (!brandingRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        branding.setId(id);
        SchoolBranding updatedBranding = brandingRepository.save(branding);
        return ResponseEntity.ok(updatedBranding);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBranding(@PathVariable String id) {
        if (!brandingRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        brandingRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}