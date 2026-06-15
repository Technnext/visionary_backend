package com.visionary_backend.controller;

import com.visionary_backend.service.JobApplicationService;
import com.visionary_backend.service.JobService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/api/careers")
public class CareersController {

    private final JobService jobService;
    private final JobApplicationService applicationService;

    public CareersController(JobService jobService, JobApplicationService applicationService) {
        this.jobService = jobService;
        this.applicationService = applicationService;
    }

    @GetMapping("/{jobId}")
    public ResponseEntity<?> getJob(@PathVariable Long jobId) {
        return jobService.getById(jobId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(value = "/apply", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> apply(
            @RequestParam("jobId")       Long jobId,
            @RequestParam("fullName")    String fullName,
            @RequestParam("email")       String email,
            @RequestParam("phone")       String phone,
            @RequestParam("location")    String location,
            @RequestParam("experience")  String experience,
            @RequestParam(value = "linkedinUrl",  required = false) String linkedinUrl,
            @RequestParam("resume")      MultipartFile resume,
            @RequestParam(value = "coverLetter", required = false)  String coverLetter) {

        try {
            applicationService.apply(jobId, fullName, email, phone, location,
                    experience, linkedinUrl, resume, coverLetter);
            return ResponseEntity.ok(Map.of("message", "Application submitted successfully."));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(Map.of("error", "Submission failed. Please try again."));
        }
    }
}
