package com.brelinx.edusuite.repository;

import com.brelinx.edusuite.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends MongoRepository<Student, String> {
    Optional<Student> findByEmail(String email);
    Optional<Student> findByStudentId(String studentId);
    List<Student> findBySchoolId(String schoolId);
    boolean existsByEmail(String email);
    boolean existsByStudentId(String studentId);
}