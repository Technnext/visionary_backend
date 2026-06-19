package com.visionary_backend.service;

import com.visionary_backend.entity.NewsletterSubscriber;
import com.visionary_backend.repository.NewsletterSubscriberRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;

@Service
public class NewsletterSubscriberService {

    private final NewsletterSubscriberRepository repository;
    private final EmailService emailService;

    @Value("${app.notification.email:info@visionaryinspire.com}")
    private String notificationEmail;

    public NewsletterSubscriberService(NewsletterSubscriberRepository repository,
                                       EmailService emailService) {
        this.repository = repository;
        this.emailService = emailService;
    }

    /**
     * Subscribes an email address.
     * @return true if newly subscribed, false if already exists.
     */
    public boolean subscribe(String email) {
        if (repository.existsByEmail(email)) {
            return false;
        }

        NewsletterSubscriber subscriber = NewsletterSubscriber.builder()
                .email(email)
                .active(true)
                .build();
        repository.save(subscriber);

        String subscriptionDate = subscriber.getSubscribedAt()
                .format(DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mm a"));

        // Notification to admin
        emailService.send(
            notificationEmail,
            "New Newsletter Subscription",
            "A new subscriber has joined the newsletter.\n\n" +
            "Email             : " + email + "\n" +
            "Subscription Date : " + subscriptionDate
        );

        // Thank-you to subscriber
        emailService.send(
            email,
            "Thank you for subscribing to Visionary Inspire",
            "Thank you for subscribing to the Visionary Inspire newsletter!\n\n" +
            "You'll receive the latest insights, updates, and news directly to your inbox.\n\n" +
            "Best regards,\nVisionary Inspire Team"
        );

        return true;
    }
}
