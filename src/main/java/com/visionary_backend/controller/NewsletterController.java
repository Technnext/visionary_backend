package com.visionary_backend.controller;

import com.visionary_backend.dto.NewsletterSubscribeRequestDTO;
import com.visionary_backend.service.NewsletterSubscriberService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/newsletter")
public class NewsletterController {

    private final NewsletterSubscriberService service;

    public NewsletterController(NewsletterSubscriberService service) {
        this.service = service;
    }

    @PostMapping("/subscribe")
    public ResponseEntity<Map<String, Object>> subscribe(
            @Valid @RequestBody NewsletterSubscribeRequestDTO request) {

        boolean subscribed = service.subscribe(request.email());

        if (subscribed) {
            return ResponseEntity.ok(Map.of(
                    "success", true,
                    "message", "Successfully subscribed. Thank you for joining us!"
            ));
        } else {
            return ResponseEntity.ok(Map.of(
                    "success", false,
                    "message", "This email is already subscribed."
            ));
        }
    }
}
