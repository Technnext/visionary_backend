package com.visionary_backend.service;

import com.visionary_backend.dto.InsightDTO;
import com.visionary_backend.exception.ResourceNotFoundException;
import com.visionary_backend.mapper.InsightMapper;
import com.visionary_backend.repository.InsightRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InsightService {

    private final InsightRepository insightRepository;

    public InsightService(InsightRepository insightRepository) {
        this.insightRepository = insightRepository;
    }

    public List<InsightDTO> getAll() {
        return InsightMapper.toDTOList(insightRepository.findAllByOrderByPublishedDateDesc());
    }

    public InsightDTO getBySlug(String slug) {
        return insightRepository.findBySlug(slug)
                .map(InsightMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Insight not found: " + slug));
    }

    public List<InsightDTO> getFeatured() {
        return InsightMapper.toDTOList(insightRepository.findByIsFeaturedTrue());
    }

    public List<InsightDTO> getByCategory(String category) {
        return InsightMapper.toDTOList(insightRepository.findByCategory(category));
    }

    public List<String> getCategories() {
        return insightRepository.findDistinctCategories();
    }
}
