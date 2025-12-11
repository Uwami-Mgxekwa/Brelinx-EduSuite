package com.brelinx.edusuite.repository;

import com.brelinx.edusuite.model.SchoolBranding;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SchoolBrandingRepository extends MongoRepository<SchoolBranding, String> {
    Optional<SchoolBranding> findBySchoolId(String schoolId);
    boolean existsBySchoolId(String schoolId);
}