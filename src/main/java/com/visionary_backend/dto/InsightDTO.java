package com.visionary_backend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;

public record InsightDTO(
        Long id,
        String slug,
        String title,
        String summary,
        String category,
        String author,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime publishedDate,
        String imageUrl,
        Boolean isFeatured
) {
}
