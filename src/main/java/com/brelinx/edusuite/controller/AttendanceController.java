package com.brelinx.edusuite.controller;

import com.brelinx.edusuite.model.Attendance;
import com.brelinx.edusuite.repository.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @GetMapping
    public ResponseEntity<List<Attendance>> getAllAttendance() {
        List<Attendance> attendance = attendanceRepository.findAll();
        
        // If no attendance records exist, create some sample data
        if (attendance.isEmpty()) {
            Attendance record1 = new Attendance();
            record1.setDate(LocalDate.now());
            record1.setCheckInTime(LocalTime.of(9, 0));
            record1.setStatus(Attendance.AttendanceStatus.PRESENT);
            record1.setNotes("On time");
            
            Attendance record2 = new Attendance();
            record2.setDate(LocalDate.now().minusDays(1));
            record2.setCheckInTime(LocalTime.of(9, 15));
            record2.setStatus(Attendance.AttendanceStatus.LATE);
            record2.setNotes("15 minutes late");
            
            Attendance record3 = new Attendance();
            record3.setDate(LocalDate.now().minusDays(2));
            record3.setStatus(Attendance.AttendanceStatus.ABSENT);
            record3.setNotes("Sick leave");
            
            attendanceRepository.save(record1);
            attendanceRepository.save(record2);
            attendanceRepository.save(record3);
            
            attendance = attendanceRepository.findAll();
        }
        
        return ResponseEntity.ok(attendance);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Attendance> getAttendanceById(@PathVariable String id) {
        Optional<Attendance> attendance = attendanceRepository.findById(id);
        return attendance.map(ResponseEntity::ok)
                        .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Attendance>> getAttendanceByStudent(@PathVariable String studentId) {
        List<Attendance> attendance = attendanceRepository.findByStudentId(studentId);
        return ResponseEntity.ok(attendance);
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<Attendance>> getAttendanceByCourse(@PathVariable String courseId) {
        List<Attendance> attendance = attendanceRepository.findByCourseId(courseId);
        return ResponseEntity.ok(attendance);
    }

    @GetMapping("/teacher/{teacherId}")
    public ResponseEntity<List<Attendance>> getAttendanceByTeacher(@PathVariable String teacherId) {
        List<Attendance> attendance = attendanceRepository.findByTeacherId(teacherId);
        return ResponseEntity.ok(attendance);
    }

    @GetMapping("/student/{studentId}/course/{courseId}")
    public ResponseEntity<List<Attendance>> getAttendanceByStudentAndCourse(
            @PathVariable String studentId, @PathVariable String courseId) {
        List<Attendance> attendance = attendanceRepository.findByStudentIdAndCourseId(studentId, courseId);
        return ResponseEntity.ok(attendance);
    }

    @GetMapping("/date-range")
    public ResponseEntity<List<Attendance>> getAttendanceByDateRange(
            @RequestParam String startDate, @RequestParam String endDate) {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        List<Attendance> attendance = attendanceRepository.findByDateBetween(start, end);
        return ResponseEntity.ok(attendance);
    }

    @PostMapping
    public ResponseEntity<Attendance> createAttendance(@RequestBody Attendance attendance) {
        if (attendance.getDate() == null) {
            attendance.setDate(LocalDate.now());
        }
        
        Attendance savedAttendance = attendanceRepository.save(attendance);
        return ResponseEntity.ok(savedAttendance);
    }

    @PostMapping("/checkin")
    public ResponseEntity<Attendance> checkIn(@RequestBody Attendance attendance) {
        attendance.setDate(LocalDate.now());
        attendance.setCheckInTime(LocalTime.now());
        
        // Determine if student is late (assuming class starts at 9:00 AM)
        LocalTime classStartTime = LocalTime.of(9, 0);
        if (LocalTime.now().isAfter(classStartTime)) {
            attendance.setStatus(Attendance.AttendanceStatus.LATE);
        } else {
            attendance.setStatus(Attendance.AttendanceStatus.PRESENT);
        }
        
        Attendance savedAttendance = attendanceRepository.save(attendance);
        return ResponseEntity.ok(savedAttendance);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Attendance> updateAttendance(@PathVariable String id, @RequestBody Attendance attendance) {
        if (!attendanceRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        attendance.setId(id);
        Attendance updatedAttendance = attendanceRepository.save(attendance);
        return ResponseEntity.ok(updatedAttendance);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAttendance(@PathVariable String id) {
        if (!attendanceRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        attendanceRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}