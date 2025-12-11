package com.brelinx.edusuite.repository;

import com.brelinx.edusuite.model.Attendance;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AttendanceRepository extends MongoRepository<Attendance, String> {
    List<Attendance> findByStudentId(String studentId);
    List<Attendance> findByCourseId(String courseId);
    List<Attendance> findByTeacherId(String teacherId);
    List<Attendance> findBySchoolId(String schoolId);
    List<Attendance> findByStudentIdAndCourseId(String studentId, String courseId);
    List<Attendance> findByDateBetween(LocalDate startDate, LocalDate endDate);
    List<Attendance> findByStatus(Attendance.AttendanceStatus status);
    List<Attendance> findByStudentIdAndDateBetween(String studentId, LocalDate startDate, LocalDate endDate);
}