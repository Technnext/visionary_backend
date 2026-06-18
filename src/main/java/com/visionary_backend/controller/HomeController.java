package com.visionary_backend.controller;

import com.visionary_backend.service.HomeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HomeController {

    private final HomeService homeService;

    public HomeController(HomeService homeService) {
        this.homeService = homeService;
    }

    @GetMapping("/home/stats")
    public ResponseEntity<?> getStats() {
        return ResponseEntity.ok(homeService.getStats());
    }

    @GetMapping("/stats")
    public ResponseEntity<?> getStatsByContext(@RequestParam String context) {
        return ResponseEntity.ok(homeService.getStatsByContext(context));
    }

    @GetMapping("/home/clients")
    public ResponseEntity<?> getClients() {
        return ResponseEntity.ok(homeService.getClients());
    }

    @GetMapping("/home/testimonials")
    public ResponseEntity<?> getTestimonials() {
        return ResponseEntity.ok(homeService.getTestimonials());
    }

    @GetMapping("/home/awards")
    public ResponseEntity<?> getAwards() {
        return ResponseEntity.ok(homeService.getAwards());
    }
}
