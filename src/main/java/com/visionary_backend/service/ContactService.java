package com.visionary_backend.service;

import com.visionary_backend.dto.ContactRequestDTO;
import com.visionary_backend.entity.ContactSubmission;
import com.visionary_backend.entity.OfficeLocation;
import com.visionary_backend.repository.ContactSubmissionRepository;
import com.visionary_backend.repository.OfficeLocationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

    private final ContactSubmissionRepository contactSubmissionRepository;
    private final OfficeLocationRepository officeLocationRepository;

    public ContactService(ContactSubmissionRepository contactSubmissionRepository,
                          OfficeLocationRepository officeLocationRepository) {
        this.contactSubmissionRepository = contactSubmissionRepository;
        this.officeLocationRepository = officeLocationRepository;
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
    }

    public List<OfficeLocation> getOfficeLocations() {
        return officeLocationRepository.findAll();
    }
}
