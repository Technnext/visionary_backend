package com.visionary_backend.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;

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

        Map<String, Object> payload = new HashMap<>();
        payload.put("from", FROM);
        payload.put("to", List.of(to));
        payload.put("subject", subject);
        payload.put("text", body);

        // Resolve to absolute path so Files.exists() is reliable regardless of working directory
        Path resolvedPath = (attachmentPath != null) ? attachmentPath.toAbsolutePath() : null;

        log.debug("[Attachment] Provided path  : {}", attachmentPath);
        log.debug("[Attachment] Absolute path  : {}", resolvedPath);

        if (resolvedPath != null && Files.exists(resolvedPath)) {
            try {
                long fileSize = Files.size(resolvedPath);
                log.debug("[Attachment] File exists    : true");
                log.debug("[Attachment] File size      : {} bytes", fileSize);

                byte[] fileBytes = Files.readAllBytes(resolvedPath);
                String encoded = Base64.getEncoder().encodeToString(fileBytes);

                // Restore original filename: strip the UUID prefix (uuid_originalname.ext)
                String storedName = resolvedPath.getFileName().toString();
                int underscoreIdx = storedName.indexOf('_');
                String originalFilename = (underscoreIdx != -1 && underscoreIdx < storedName.length() - 1)
                        ? storedName.substring(underscoreIdx + 1)
                        : storedName;

                log.debug("[Attachment] Filename sent  : {}", originalFilename);
                log.debug("[Attachment] Base64 length  : {} chars", encoded.length());

                payload.put("attachments", List.of(
                    Map.of("filename", originalFilename, "content", encoded)
                ));
            } catch (IOException e) {
                log.error("[Attachment] Failed to read file at {} — sending without attachment", resolvedPath, e);
            }
        } else {
            log.warn("[Attachment] File exists    : false — path={} — sending without attachment", resolvedPath);
        }

        try {
            restClient.post()
                .uri(RESEND_API_URL)
                .header("Authorization", "Bearer " + apiKey)
                .contentType(MediaType.APPLICATION_JSON)
                .body(payload)
                .retrieve()
                .toBodilessEntity();
            boolean hasAttachment = payload.containsKey("attachments");
            log.debug("[Resend] Email accepted — to={} subject=\"{}\" attachment={}", to, subject, hasAttachment);
        } catch (RestClientResponseException e) {
            log.error("[Resend] API rejected request — status={} body={}",
                e.getStatusCode(), e.getResponseBodyAsString(), e);
        } catch (Exception e) {
            log.error("[Resend] Failed to send email to={} subject=\"{}\"", to, subject, e);
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
