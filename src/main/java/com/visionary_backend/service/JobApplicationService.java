package com.visionary_backend.service;

import com.visionary_backend.entity.Job;
import com.visionary_backend.entity.JobApplication;
import com.visionary_backend.repository.JobApplicationRepository;
import com.visionary_backend.repository.JobRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
public class JobApplicationService {

    private final JobApplicationRepository applicationRepository;
    private final JobRepository jobRepository;
    private final EmailService emailService;

    @Value("${app.upload.dir:uploads/resumes}")
    private String uploadDir;

    @Value("${app.notification.email:info@visionaryinspire.com}")
    private String notificationEmail;

    public JobApplicationService(JobApplicationRepository applicationRepository,
                                 JobRepository jobRepository,
                                 EmailService emailService) {
        this.applicationRepository = applicationRepository;
        this.jobRepository = jobRepository;
        this.emailService = emailService;
    }

    public JobApplication apply(Long jobId, String fullName, String email,
                                String phone, String location, String experience,
                                String linkedinUrl, MultipartFile resume,
                                String coverLetter) throws IOException {

        String jobTitle = jobRepository.findById(jobId)
                .map(Job::getTitle)
                .orElse("Unknown Position");

        Path dir = Paths.get(uploadDir);
        Files.createDirectories(dir);
        String filename = UUID.randomUUID() + "_" + resume.getOriginalFilename();
        Path dest = dir.resolve(filename);
        Files.copy(resume.getInputStream(), dest, StandardCopyOption.REPLACE_EXISTING);

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

        String applicationDate = app.getCreatedAt()
                .format(DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mm a"));

        // Notification to admin
        emailService.send(
            notificationEmail,
            "New Job Application Received - " + jobTitle,
            "A new job application has been submitted.\n\n" +
            "Name              : " + fullName + "\n" +
            "Email             : " + email + "\n" +
            "Phone             : " + phone + "\n" +
            "Position          : " + jobTitle + "\n" +
            "Location          : " + location + "\n" +
            "Experience        : " + experience + "\n" +
            (linkedinUrl != null && !linkedinUrl.isBlank() ? "LinkedIn          : " + linkedinUrl + "\n" : "") +
            "Application Date  : " + applicationDate
        );

        // Acknowledgement to applicant
        emailService.send(
            email,
            "Thank you for applying to Visionary Inspire",
            "Dear " + fullName + ",\n\n" +
            "Thank you for applying for the " + jobTitle + " position at Visionary Inspire.\n\n" +
            "We have received your application and will review it shortly. " +
            "If your profile matches our requirements, we will be in touch.\n\n" +
            "Best regards,\nVisionary Inspire Team"
        );

        return app;
    }
}
