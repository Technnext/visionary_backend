package com.visionary_backend.service;

import com.visionary_backend.dto.ContactRequestDTO;
import com.visionary_backend.entity.ContactSubmission;
import com.visionary_backend.entity.OfficeLocation;
import com.visionary_backend.repository.ContactSubmissionRepository;
import com.visionary_backend.repository.OfficeLocationRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ContactService {

    private final ContactSubmissionRepository contactSubmissionRepository;
    private final OfficeLocationRepository officeLocationRepository;
    private final EmailService emailService;

    @Value("${app.notification.email:info@visionaryinspire.com}")
    private String notificationEmail;

    public ContactService(ContactSubmissionRepository contactSubmissionRepository,
                          OfficeLocationRepository officeLocationRepository,
                          EmailService emailService) {
        this.contactSubmissionRepository = contactSubmissionRepository;
        this.officeLocationRepository = officeLocationRepository;
        this.emailService = emailService;
    }

    public void submit(ContactRequestDTO request) {
        ContactSubmission submission = ContactSubmission.builder()
                .name(request.name())
                .email(request.email())
                .phone(request.phone())
                .company(request.company())
                .subject(request.subject())
                .message(request.message())
                .build();
        contactSubmissionRepository.save(submission);

        String submissionDate = submission.getSubmittedAt()
                .format(DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mm a"));

        emailService.send(
            notificationEmail,
            "New Contact Form Submission",
            "A new contact form submission has been received.\n\n" +
            "Name             : " + request.name() + "\n" +
            "Email            : " + request.email() + "\n" +
            "Phone            : " + (request.phone() != null ? request.phone() : "-") + "\n" +
            "Company          : " + (request.company() != null ? request.company() : "-") + "\n" +
            "Message          : " + request.message() + "\n" +
            "Submission Date  : " + submissionDate
        );
    }

    public List<OfficeLocation> getOfficeLocations() {
        return officeLocationRepository.findAll();
    }
}
