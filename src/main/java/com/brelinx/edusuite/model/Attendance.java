package com.brelinx.edusuite.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalTime;

@Document(collection = "attendance")
@Getter
@Setter
public class Attendance extends BaseEntity {

    private String studentId;
    private String courseId;
    private String teacherId;
    private String schoolId;
    
    private LocalDate date;
    private LocalTime checkInTime;
    private LocalTime checkOutTime;
    
    private AttendanceStatus status; // PRESENT, ABSENT, LATE, EXCUSED
    private String notes;
    
    public enum AttendanceStatus {
        PRESENT,
        ABSENT,
        LATE,
        EXCUSED
    }
    
    // Helper methods
    public boolean isPresent() {
        return status == AttendanceStatus.PRESENT || status == AttendanceStatus.LATE;
    }
    
    public long getMinutesLate() {
        if (checkInTime != null && status == AttendanceStatus.LATE) {
            // Assuming class starts at 9:00 AM
            LocalTime classStartTime = LocalTime.of(9, 0);
            if (checkInTime.isAfter(classStartTime)) {
                return java.time.Duration.between(classStartTime, checkInTime).toMinutes();
            }
        }
        return 0;
    }
}