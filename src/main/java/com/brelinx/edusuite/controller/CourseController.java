package com.brelinx.edusuite.controller;

import com.brelinx.edusuite.model.Course;
import com.brelinx.edusuite.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        
        // If no courses exist, create some sample data
        if (courses.isEmpty()) {
            Course course1 = new Course();
            course1.setName("Advanced Mathematics");
            course1.setCourseCode("MATH301");
            course1.setDescription("Advanced calculus and linear algebra");
            course1.setCredits(4);
            course1.setStartDate(LocalDate.of(2024, 9, 1));
            course1.setEndDate(LocalDate.of(2024, 12, 15));
            
            Course course2 = new Course();
            course2.setName("Introduction to Programming");
            course2.setCourseCode("CS101");
            course2.setDescription("Fundamentals of computer programming using Java");
            course2.setCredits(3);
            course2.setStartDate(LocalDate.of(2024, 9, 1));
            course2.setEndDate(LocalDate.of(2024, 12, 15));
            
            Course course3 = new Course();
            course3.setName("Data Structures & Algorithms");
            course3.setCourseCode("CS201");
            course3.setDescription("Advanced programming concepts and algorithm design");
            course3.setCredits(4);
            course3.setStartDate(LocalDate.of(2024, 9, 1));
            course3.setEndDate(LocalDate.of(2024, 12, 15));
            
            courseRepository.save(course1);
            courseRepository.save(course2);
            courseRepository.save(course3);
            
            courses = courseRepository.findAll();
        }
        
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable String id) {
        Optional<Course> course = courseRepository.findById(id);
        return course.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/school/{schoolId}")
    public ResponseEntity<List<Course>> getCoursesBySchool(@PathVariable String schoolId) {
        List<Course> courses = courseRepository.findBySchoolId(schoolId);
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/teacher/{teacherId}")
    public ResponseEntity<List<Course>> getCoursesByTeacher(@PathVariable String teacherId) {
        List<Course> courses = courseRepository.findByTeacherId(teacherId);
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Course>> getCoursesByStudent(@PathVariable String studentId) {
        List<Course> courses = courseRepository.findByStudentIdsContaining(studentId);
        return ResponseEntity.ok(courses);
    }

    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        if (courseRepository.existsByCourseCode(course.getCourseCode())) {
            return ResponseEntity.badRequest().build();
        }
        Course savedCourse = courseRepository.save(course);
        return ResponseEntity.ok(savedCourse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable String id, @RequestBody Course course) {
        if (!courseRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        course.setId(id);
        Course updatedCourse = courseRepository.save(course);
        return ResponseEntity.ok(updatedCourse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable String id) {
        if (!courseRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        courseRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{courseId}/students/{studentId}")
    public ResponseEntity<Course> enrollStudent(@PathVariable String courseId, @PathVariable String studentId) {
        Optional<Course> courseOpt = courseRepository.findById(courseId);
        if (courseOpt.isPresent()) {
            Course course = courseOpt.get();
            course.addStudentId(studentId);
            Course updatedCourse = courseRepository.save(course);
            return ResponseEntity.ok(updatedCourse);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{courseId}/students/{studentId}")
    public ResponseEntity<Course> unenrollStudent(@PathVariable String courseId, @PathVariable String studentId) {
        Optional<Course> courseOpt = courseRepository.findById(courseId);
        if (courseOpt.isPresent()) {
            Course course = courseOpt.get();
            course.removeStudentId(studentId);
            Course updatedCourse = courseRepository.save(course);
            return ResponseEntity.ok(updatedCourse);
        }
        return ResponseEntity.notFound().build();
    }
}