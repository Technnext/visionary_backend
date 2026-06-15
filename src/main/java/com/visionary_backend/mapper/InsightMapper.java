package com.visionary_backend.mapper;

import com.visionary_backend.dto.InsightDTO;
import com.visionary_backend.entity.Insight;

import java.util.Collections;
import java.util.List;

public class InsightMapper {

    private InsightMapper() {}

    public static InsightDTO toDTO(Insight entity) {
        if (entity == null) return null;
        return new InsightDTO(
                entity.getId(),
                entity.getSlug(),
                entity.getTitle(),
                entity.getSummary(),
                entity.getCategory(),
                entity.getAuthor(),
                entity.getPublishedDate(),
                entity.getImageUrl(),
                entity.getIsFeatured()
        );
    }

    public static List<InsightDTO> toDTOList(List<Insight> entities) {
        if (entities == null) return Collections.emptyList();
        return entities.stream().map(InsightMapper::toDTO).toList();
    }
}
