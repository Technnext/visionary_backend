package com.visionary_backend.mapper;

import com.visionary_backend.dto.IndustryDTO;
import com.visionary_backend.dto.IndustrySolutionDTO;
import com.visionary_backend.entity.Industry;
import com.visionary_backend.entity.IndustrySolution;

import java.util.Collections;
import java.util.List;

public class IndustryMapper {

    private IndustryMapper() {}

    public static IndustrySolutionDTO toDTO(IndustrySolution solution) {
        if (solution == null) return null;
        return new IndustrySolutionDTO(
                solution.getId(),
                solution.getTitle(),
                solution.getDescription()
        );
    }

    public static IndustryDTO toDTO(Industry entity) {
        if (entity == null) return null;

        List<IndustrySolutionDTO> solutions = entity.getSolutions() != null
                ? entity.getSolutions().stream().map(IndustryMapper::toDTO).toList()
                : Collections.emptyList();

        return new IndustryDTO(
                entity.getId(),
                entity.getSlug(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getIconUrl(),
                entity.getBannerUrl(),
                solutions
        );
    }

    public static List<IndustryDTO> toDTOList(List<Industry> entities) {
        if (entities == null) return Collections.emptyList();
        return entities.stream().map(IndustryMapper::toDTO).toList();
    }
}
