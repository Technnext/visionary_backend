package com.visionary_backend.controller;

import com.visionary_backend.service.MortgageServiceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mortgage-services")
public class MortgageServiceController {

    private final MortgageServiceService mortgageServiceService;

    public MortgageServiceController(MortgageServiceService mortgageServiceService) {
        this.mortgageServiceService = mortgageServiceService;
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(mortgageServiceService.getAll());
    }

    @GetMapping("/{slug}")
    public ResponseEntity<?> getBySlug(@PathVariable String slug) {
        return ResponseEntity.ok(mortgageServiceService.getBySlug(slug));
    }
}
