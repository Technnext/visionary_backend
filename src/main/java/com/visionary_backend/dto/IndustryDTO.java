package com.visionary_backend.dto;

import java.util.List;

public record IndustryDTO(
        Long id,
        String slug,
        String title,
        String description,
        String iconUrl,
        String bannerUrl,
        List<IndustrySolutionDTO> solutions
) {
}
