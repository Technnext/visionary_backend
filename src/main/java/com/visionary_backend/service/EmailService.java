package com.visionary_backend.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;
import java.util.HashMap;
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

    public void sendWithAttachment(String to, String subject, String body, Path attachmentPath) {
        if (apiKey == null || apiKey.isBlank()) {
            log.warn("Resend API key not configured — skipping email to {}", to);
            return;
        }
        try {
            Map<String, Object> payload = new HashMap<>();
            payload.put("from", FROM);
            payload.put("to", List.of(to));
            payload.put("subject", subject);
            payload.put("text", body);

            if (attachmentPath != null && Files.exists(attachmentPath)) {
                byte[] fileBytes = Files.readAllBytes(attachmentPath);
                String encoded = Base64.getEncoder().encodeToString(fileBytes);
                payload.put("attachments", List.of(
                    Map.of("filename", attachmentPath.getFileName().toString(), "content", encoded)
                ));
            }

            restClient.post()
                .uri(RESEND_API_URL)
                .header("Authorization", "Bearer " + apiKey)
                .contentType(MediaType.APPLICATION_JSON)
                .body(payload)
                .retrieve()
                .toBodilessEntity();
            log.debug("Email with attachment sent via Resend to {}", to);
        } catch (IOException e) {
            log.error("Failed to read attachment for email to {}: {}", to, e.getMessage());
            send(to, subject, body);
        } catch (Exception e) {
            log.error("Failed to send email with attachment via Resend to {}: {}", to, e.getMessage());
        }
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
