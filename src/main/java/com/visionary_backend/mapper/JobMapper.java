package com.visionary_backend.mapper;

import com.visionary_backend.dto.JobDTO;
import com.visionary_backend.entity.Job;

import java.util.Collections;
import java.util.List;

public class JobMapper {

    private JobMapper() {}

    public static JobDTO toDTO(Job entity) {
        if (entity == null) return null;
        return new JobDTO(
                entity.getId(),
                entity.getTitle(),
                entity.getDepartment(),
                entity.getLocation(),
                entity.getType(),
                entity.getDescription(),
                entity.getPostedDate()
        );
    }

    public static List<JobDTO> toDTOList(List<Job> entities) {
        if (entities == null) return Collections.emptyList();
        return entities.stream().map(JobMapper::toDTO).toList();
    }
}
