package com.visionary_backend.dto;

import java.util.List;

public record ServiceDTO(
        Long id,
        String slug,
        String title,
        String description,
        String iconUrl,
        String bannerUrl,
        String category,
        List<ServiceBenefitDTO> benefits
) {
}
