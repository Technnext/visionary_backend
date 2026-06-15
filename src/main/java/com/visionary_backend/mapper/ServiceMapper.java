package com.visionary_backend.mapper;

import com.visionary_backend.dto.ServiceBenefitDTO;
import com.visionary_backend.dto.ServiceDTO;
import com.visionary_backend.entity.ServiceBenefit;
import com.visionary_backend.entity.ServiceEntity;

import java.util.Collections;
import java.util.List;

public class ServiceMapper {

    private ServiceMapper() {}

    public static ServiceBenefitDTO toDTO(ServiceBenefit benefit) {
        if (benefit == null) return null;
        return new ServiceBenefitDTO(
                benefit.getId(),
                benefit.getTitle(),
                benefit.getDescription()
        );
    }

    public static ServiceDTO toDTO(ServiceEntity entity) {
        if (entity == null) return null;

        List<ServiceBenefitDTO> benefits = entity.getBenefits() != null
                ? entity.getBenefits().stream().map(ServiceMapper::toDTO).toList()
                : Collections.emptyList();

        return new ServiceDTO(
                entity.getId(),
                entity.getSlug(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getIconUrl(),
                entity.getBannerUrl(),
                entity.getCategory(),
                benefits
        );
    }

    public static List<ServiceDTO> toDTOList(List<ServiceEntity> entities) {
        if (entities == null) return Collections.emptyList();
        return entities.stream().map(ServiceMapper::toDTO).toList();
    }
}
