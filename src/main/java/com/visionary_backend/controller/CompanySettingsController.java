package com.visionary_backend.controller;

import com.visionary_backend.service.CompanySettingsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/company-settings")
public class CompanySettingsController {

    private final CompanySettingsService companySettingsService;

    public CompanySettingsController(CompanySettingsService companySettingsService) {
        this.companySettingsService = companySettingsService;
    }

    @GetMapping
    public ResponseEntity<?> getSettings() {
        return companySettingsService.getSettings()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
