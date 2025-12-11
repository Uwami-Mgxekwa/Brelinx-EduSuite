package com.brelinx.edusuite.repository;

import com.brelinx.edusuite.model.Teacher;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeacherRepository extends MongoRepository<Teacher, String> {
    Optional<Teacher> findByEmail(String email);
    Optional<Teacher> findByEmployeeId(String employeeId);
    List<Teacher> findBySchoolId(String schoolId);
    List<Teacher> findByDepartment(String department);
    boolean existsByEmail(String email);
    boolean existsByEmployeeId(String employeeId);
}