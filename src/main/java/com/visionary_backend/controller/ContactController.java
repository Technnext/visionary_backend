package com.visionary_backend.controller;

import com.visionary_backend.dto.ContactRequestDTO;
import com.visionary_backend.service.ContactService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/contact")
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping
    public ResponseEntity<?> submit(@Valid @RequestBody ContactRequestDTO request) {
        contactService.submit(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of("message", "Your message has been received. We will be in touch shortly."));
    }

    @GetMapping("/offices")
    public ResponseEntity<?> getOffices() {
        return ResponseEntity.ok(contactService.getOfficeLocations());
    }
}
