package com.brelinx.edusuite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "index.html";
    }

    @GetMapping("/api/status")
    @ResponseBody
    public Map<String, Object> apiStatus() {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Welcome to Brelinx EduSuite API!");
        response.put("timestamp", LocalDateTime.now());
        response.put("status", "running");
        response.put("endpoints", Map.of(
            "schools", "/api/schools",
            "students", "/api/students",
            "teachers", "/api/teachers",
            "courses", "/api/courses",
            "grades", "/api/grades",
            "attendance", "/api/attendance",
            "dashboard", "/api/dashboard",
            "swagger", "/swagger-ui.html"
        ));
        return response;
    }

    @GetMapping("/api")
    @ResponseBody
    public Map<String, Object> apiInfo() {
        Map<String, Object> response = new HashMap<>();
        response.put("name", "Brelinx EduSuite API");
        response.put("version", "1.0.0");
        response.put("description", "Student Management System API");
        Map<String, String> endpoints = new HashMap<>();
        endpoints.put("GET /api/schools", "Get all schools");
        endpoints.put("POST /api/schools", "Create new school");
        endpoints.put("GET /api/students", "Get all students");
        endpoints.put("POST /api/students", "Create new student");
        endpoints.put("GET /api/teachers", "Get all teachers");
        endpoints.put("POST /api/teachers", "Create new teacher");
        endpoints.put("GET /api/courses", "Get all courses");
        endpoints.put("POST /api/courses", "Create new course");
        endpoints.put("GET /api/grades", "Get all grades");
        endpoints.put("POST /api/grades", "Create new grade");
        endpoints.put("GET /api/attendance", "Get attendance records");
        endpoints.put("POST /api/attendance", "Record attendance");
        endpoints.put("GET /api/dashboard/stats", "Get dashboard statistics");
        endpoints.put("GET /api/dashboard/analytics", "Get analytics data");
        
        response.put("endpoints", endpoints);
        return response;
    }
}