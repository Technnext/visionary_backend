package com.visionary_backend.controller;

import com.visionary_backend.entity.NavigationLink;
import com.visionary_backend.repository.NavigationLinkRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/navigation-links")
public class NavigationLinkController {

    private final NavigationLinkRepository navigationLinkRepository;

    public NavigationLinkController(NavigationLinkRepository navigationLinkRepository) {
        this.navigationLinkRepository = navigationLinkRepository;
    }

    @GetMapping
    public ResponseEntity<List<NavigationLink>> get(
            @RequestParam(required = false) String section) {
        List<NavigationLink> links = (section != null && !section.isBlank())
                ? navigationLinkRepository.findBySectionAndIsActiveTrueOrderByDisplayOrderAsc(section)
                : navigationLinkRepository.findByIsActiveTrueOrderBySectionAscDisplayOrderAsc();
        return ResponseEntity.ok(links);
    }
}
