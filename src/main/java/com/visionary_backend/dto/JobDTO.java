package com.visionary_backend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;

public record JobDTO(
        Long id,
        String title,
        String department,
        String location,
        String type,
        String description,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime postedDate
) {
}
