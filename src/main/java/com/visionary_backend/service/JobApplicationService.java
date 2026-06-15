package com.visionary_backend.service;

import com.visionary_backend.entity.Job;
import com.visionary_backend.entity.JobApplication;
import com.visionary_backend.repository.JobApplicationRepository;
import com.visionary_backend.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class JobApplicationService {

    private final JobApplicationRepository applicationRepository;
    private final JobRepository jobRepository;

    @Autowired(required = false)
    private JavaMailSender mailSender;

    @Value("${app.upload.dir:uploads/resumes}")
    private String uploadDir;

    @Value("${app.notification.email:info@visionaryinspire.com}")
    private String notificationEmail;

    public JobApplicationService(JobApplicationRepository applicationRepository,
                                 JobRepository jobRepository) {
        this.applicationRepository = applicationRepository;
        this.jobRepository = jobRepository;
    }

    public JobApplication apply(Long jobId, String fullName, String email,
                                String phone, String location, String experience,
                                String linkedinUrl, MultipartFile resume,
                                String coverLetter) throws IOException {

        String jobTitle = jobRepository.findById(jobId)
                .map(Job::getTitle)
                .orElse("Unknown Position");

        // Persist resume file
        Path dir = Paths.get(uploadDir);
        Files.createDirectories(dir);
        String filename = UUID.randomUUID() + "_" + resume.getOriginalFilename();
        Path dest = dir.resolve(filename);
        Files.copy(resume.getInputStream(), dest, StandardCopyOption.REPLACE_EXISTING);

        // Save application record
        JobApplication app = JobApplication.builder()
                .jobId(jobId)
                .fullName(fullName)
                .email(email)
                .phone(phone)
                .location(location)
                .experience(experience)
                .linkedinUrl(linkedinUrl)
                .resumePath(dest.toString())
                .coverLetter(coverLetter)
                .jobTitle(jobTitle)
                .build();

        applicationRepository.save(app);

        // Send notification — skipped if mail not configured
        try {
            if (mailSender != null) {
            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setTo(notificationEmail);
            msg.setSubject("New Job Application: " + jobTitle);
            msg.setText(
                "New application received.\n\n" +
                "Candidate : " + fullName + "\n" +
                "Job Title  : " + jobTitle + "\n" +
                "Email      : " + email + "\n" +
                "Phone      : " + phone + "\n" +
                "Location   : " + location + "\n" +
                "Experience : " + experience + "\n" +
                (linkedinUrl != null && !linkedinUrl.isBlank() ? "LinkedIn   : " + linkedinUrl + "\n" : "") +
                "Resume     : " + filename
            );
                mailSender.send(msg);
            }
        } catch (Exception ignored) {
            // Swallow — email misconfiguration should not affect applicant
        }

        return app;
    }
}
