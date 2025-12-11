package com.brelinx.edusuite.controller;

import com.brelinx.edusuite.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    private SchoolRepository schoolRepository;
    
    @Autowired
    private StudentRepository studentRepository;
    
    @Autowired
    private TeacherRepository teacherRepository;
    
    @Autowired
    private CourseRepository courseRepository;
    
    @Autowired
    private GradeRepository gradeRepository;
    
    @Autowired
    private AttendanceRepository attendanceRepository;

    @GetMapping("/stats")
    public ResponseEntity<Map<String, Object>> getDashboardStats() {
        Map<String, Object> stats = new HashMap<>();
        
        // Basic counts
        stats.put("totalSchools", schoolRepository.count());
        stats.put("totalStudents", studentRepository.count());
        stats.put("totalTeachers", teacherRepository.count());
        stats.put("totalCourses", courseRepository.count());
        stats.put("totalGrades", gradeRepository.count());
        stats.put("totalAttendanceRecords", attendanceRepository.count());
        
        // Today's stats
        LocalDate today = LocalDate.now();
        stats.put("todayAttendance", attendanceRepository.findByDateBetween(today, today).size());
        stats.put("recentGrades", gradeRepository.findByDateGradedBetween(today.minusDays(7), today).size());
        
        return ResponseEntity.ok(stats);
    }

    @GetMapping("/school/{schoolId}/stats")
    public ResponseEntity<Map<String, Object>> getSchoolStats(@PathVariable String schoolId) {
        Map<String, Object> stats = new HashMap<>();
        
        stats.put("students", studentRepository.findBySchoolId(schoolId).size());
        stats.put("teachers", teacherRepository.findBySchoolId(schoolId).size());
        stats.put("courses", courseRepository.findBySchoolId(schoolId).size());
        stats.put("grades", gradeRepository.findBySchoolId(schoolId).size());
        stats.put("attendanceRecords", attendanceRepository.findBySchoolId(schoolId).size());
        
        return ResponseEntity.ok(stats);
    }

    @GetMapping("/analytics")
    public ResponseEntity<Map<String, Object>> getAnalytics() {
        Map<String, Object> analytics = new HashMap<>();
        
        // Grade distribution
        Map<String, Long> gradeDistribution = new HashMap<>();
        gradeDistribution.put("A", gradeRepository.findAll().stream()
            .filter(g -> "A".equals(g.getLetterGrade())).count());
        gradeDistribution.put("B", gradeRepository.findAll().stream()
            .filter(g -> "B".equals(g.getLetterGrade())).count());
        gradeDistribution.put("C", gradeRepository.findAll().stream()
            .filter(g -> "C".equals(g.getLetterGrade())).count());
        gradeDistribution.put("D", gradeRepository.findAll().stream()
            .filter(g -> "D".equals(g.getLetterGrade())).count());
        gradeDistribution.put("F", gradeRepository.findAll().stream()
            .filter(g -> "F".equals(g.getLetterGrade())).count());
        
        analytics.put("gradeDistribution", gradeDistribution);
        
        // Attendance statistics
        LocalDate weekAgo = LocalDate.now().minusDays(7);
        LocalDate today = LocalDate.now();
        
        long presentCount = attendanceRepository.findByDateBetween(weekAgo, today).stream()
            .filter(a -> a.getStatus().toString().equals("PRESENT")).count();
        long absentCount = attendanceRepository.findByDateBetween(weekAgo, today).stream()
            .filter(a -> a.getStatus().toString().equals("ABSENT")).count();
        long lateCount = attendanceRepository.findByDateBetween(weekAgo, today).stream()
            .filter(a -> a.getStatus().toString().equals("LATE")).count();
        
        Map<String, Long> attendanceStats = new HashMap<>();
        attendanceStats.put("present", presentCount);
        attendanceStats.put("absent", absentCount);
        attendanceStats.put("late", lateCount);
        
        analytics.put("weeklyAttendance", attendanceStats);
        
        return ResponseEntity.ok(analytics);
    }

    @GetMapping("/recent-activity")
    public ResponseEntity<Map<String, Object>> getRecentActivity() {
        Map<String, Object> activity = new HashMap<>();
        
        LocalDate weekAgo = LocalDate.now().minusDays(7);
        LocalDate today = LocalDate.now();
        
        activity.put("recentGrades", gradeRepository.findByDateGradedBetween(weekAgo, today));
        activity.put("recentAttendance", attendanceRepository.findByDateBetween(weekAgo, today));
        
        return ResponseEntity.ok(activity);
    }
}