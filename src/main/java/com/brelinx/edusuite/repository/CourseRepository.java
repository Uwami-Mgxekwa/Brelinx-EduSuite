package com.brelinx.edusuite.repository;

import com.brelinx.edusuite.model.Course;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends MongoRepository<Course, String> {
    Optional<Course> findByCourseCode(String courseCode);
    List<Course> findBySchoolId(String schoolId);
    List<Course> findByTeacherId(String teacherId);
    List<Course> findByStudentIdsContaining(String studentId);
    boolean existsByCourseCode(String courseCode);
}