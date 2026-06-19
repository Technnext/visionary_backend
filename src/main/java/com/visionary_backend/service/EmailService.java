package com.visionary_backend.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Map;

@Service
public class EmailService {

    private static final Logger log = LoggerFactory.getLogger(EmailService.class);
    private static final String RESEND_API_URL = "https://api.resend.com/emails";
    private static final String FROM = "Visionary Inspire <noreply@visionaryinspire.com>";

    private final RestClient restClient;

    @Value("${resend.api.key:}")
    private String apiKey;

    public EmailService() {
        this.restClient = RestClient.create();
    }

    public void send(String to, String subject, String body) {
        if (apiKey == null || apiKey.isBlank()) {
            log.warn("Resend API key not configured — skipping email to {}", to);
            return;
        }
        try {
            Map<String, Object> payload = Map.of(
                "from", FROM,
                "to", List.of(to),
                "subject", subject,
                "text", body
            );
            restClient.post()
                .uri(RESEND_API_URL)
                .header("Authorization", "Bearer " + apiKey)
                .contentType(MediaType.APPLICATION_JSON)
                .body(payload)
                .retrieve()
                .toBodilessEntity();
            log.debug("Email sent via Resend to {}", to);
        } catch (Exception e) {
            log.error("Failed to send email via Resend to {}: {}", to, e.getMessage());
        }
    }
}
