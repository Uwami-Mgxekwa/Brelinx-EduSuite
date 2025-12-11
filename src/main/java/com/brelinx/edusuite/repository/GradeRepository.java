package com.brelinx.edusuite.repository;

import com.brelinx.edusuite.model.Grade;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface GradeRepository extends MongoRepository<Grade, String> {
    List<Grade> findByStudentId(String studentId);
    List<Grade> findByCourseId(String courseId);
    List<Grade> findByTeacherId(String teacherId);
    List<Grade> findBySchoolId(String schoolId);
    List<Grade> findByStudentIdAndCourseId(String studentId, String courseId);
    List<Grade> findByDateGradedBetween(LocalDate startDate, LocalDate endDate);
    List<Grade> findByAssignmentType(String assignmentType);
}