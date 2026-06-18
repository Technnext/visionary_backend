package com.visionary_backend.controller;

import com.visionary_backend.entity.HeroSection;
import com.visionary_backend.repository.HeroSectionRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/hero-sections")
public class HeroSectionController {

    private final HeroSectionRepository heroSectionRepository;

    public HeroSectionController(HeroSectionRepository heroSectionRepository) {
        this.heroSectionRepository = heroSectionRepository;
    }

    @GetMapping
    public ResponseEntity<?> get(@RequestParam(required = false) String pageKey) {
        if (pageKey != null && !pageKey.isBlank()) {
            return heroSectionRepository.findByPageKeyAndIsActiveTrue(pageKey)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        }
        List<HeroSection> all = heroSectionRepository.findByIsActiveTrueOrderByDisplayOrderAsc();
        return ResponseEntity.ok(all);
    }
}
