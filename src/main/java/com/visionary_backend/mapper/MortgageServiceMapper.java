package com.visionary_backend.mapper;

import com.visionary_backend.dto.MortgageServiceDTO;
import com.visionary_backend.entity.MortgageService;

import java.util.Collections;
import java.util.List;

public class MortgageServiceMapper {

    private MortgageServiceMapper() {}

    public static MortgageServiceDTO toDTO(MortgageService entity) {
        if (entity == null) return null;
        return new MortgageServiceDTO(
                entity.getId(),
                entity.getSlug(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getOverview(),
                entity.getCategory(),
                entity.getImageUrl(),
                entity.getBannerUrl(),
                entity.getDisplayOrder()
        );
    }

    public static List<MortgageServiceDTO> toDTOList(List<MortgageService> entities) {
        if (entities == null) return Collections.emptyList();
        return entities.stream().map(MortgageServiceMapper::toDTO).toList();
    }
}
