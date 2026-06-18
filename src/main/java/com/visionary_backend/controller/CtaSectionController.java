package com.visionary_backend.controller;

import com.visionary_backend.entity.CtaSection;
import com.visionary_backend.repository.CtaSectionRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cta-sections")
public class CtaSectionController {

    private final CtaSectionRepository ctaSectionRepository;

    public CtaSectionController(CtaSectionRepository ctaSectionRepository) {
        this.ctaSectionRepository = ctaSectionRepository;
    }

    @GetMapping
    public ResponseEntity<?> get(@RequestParam(required = false) String pageKey) {
        if (pageKey != null && !pageKey.isBlank()) {
            return ctaSectionRepository.findByPageKeyAndIsActiveTrue(pageKey)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        }
        List<CtaSection> all = ctaSectionRepository.findByIsActiveTrueOrderByDisplayOrderAsc();
        return ResponseEntity.ok(all);
    }
}
