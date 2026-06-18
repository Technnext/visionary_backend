package com.visionary_backend.service;

import com.visionary_backend.entity.CompanySettings;
import com.visionary_backend.repository.CompanySettingsRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompanySettingsService {

    private final CompanySettingsRepository companySettingsRepository;

    public CompanySettingsService(CompanySettingsRepository companySettingsRepository) {
        this.companySettingsRepository = companySettingsRepository;
    }

    public Optional<CompanySettings> getSettings() {
        return companySettingsRepository.findAll().stream().findFirst();
    }
}
