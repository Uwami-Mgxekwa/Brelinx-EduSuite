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
            "swagger", "/swagger-ui.html",
            "h2-console", "/h2-console"
        ));
        return response;
    }

    @GetMapping("/api")
    public Map<String, Object> apiInfo() {
        Map<String, Object> response = new HashMap<>();
        response.put("name", "Brelinx EduSuite API");
        response.put("version", "1.0.0");
        response.put("description", "Student Management System API");
        response.put("endpoints", Map.of(
            "GET /api/schools", "Get all schools",
            "GET /api/schools/{id}", "Get school by ID",
            "POST /api/schools", "Create new school",
            "GET /api/students", "Get all students",
            "GET /api/students/{id}", "Get student by ID",
            "POST /api/students", "Create new student"
        ));
        return response;
    }
}