package com.visionary_backend.dto;

public record MortgageServiceDTO(
        Long id,
        String slug,
        String title,
        String description,
        String overview,
        String category,
        String imageUrl,
        String bannerUrl,
        Integer displayOrder
) {
}
