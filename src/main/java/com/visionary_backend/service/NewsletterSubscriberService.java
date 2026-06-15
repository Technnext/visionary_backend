package com.visionary_backend.service;

import com.visionary_backend.entity.NewsletterSubscriber;
import com.visionary_backend.repository.NewsletterSubscriberRepository;
import org.springframework.stereotype.Service;

@Service
public class NewsletterSubscriberService {

    private final NewsletterSubscriberRepository repository;

    public NewsletterSubscriberService(NewsletterSubscriberRepository repository) {
        this.repository = repository;
    }

    /**
     * Subscribes an email address.
     * @return true if newly subscribed, false if already exists.
     */
    public boolean subscribe(String email) {
        if (repository.existsByEmail(email)) {
            return false;
        }
        repository.save(NewsletterSubscriber.builder()
                .email(email)
                .active(true)
                .build());
        return true;
    }
}
