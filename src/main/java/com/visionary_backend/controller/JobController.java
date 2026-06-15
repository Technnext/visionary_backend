package com.visionary_backend.controller;

import com.visionary_backend.service.JobService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public ResponseEntity<?> getAll(
            @RequestParam(required = false) String department,
            @RequestParam(required = false) String location) {

        if (department != null && !department.isBlank()) {
            return ResponseEntity.ok(jobService.getByDepartment(department));
        }
        if (location != null && !location.isBlank()) {
            return ResponseEntity.ok(jobService.getByLocation(location));
        }
        return ResponseEntity.ok(jobService.getAll());
    }
}
