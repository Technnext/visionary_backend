package com.visionary_backend.service;

import com.visionary_backend.dto.IndustryDTO;
import com.visionary_backend.exception.ResourceNotFoundException;
import com.visionary_backend.mapper.IndustryMapper;
import com.visionary_backend.repository.IndustryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndustryService {

    private final IndustryRepository industryRepository;

    public IndustryService(IndustryRepository industryRepository) {
        this.industryRepository = industryRepository;
    }

    public List<IndustryDTO> getAll() {
        return IndustryMapper.toDTOList(industryRepository.findAllByOrderByDisplayOrderAsc());
    }

    public IndustryDTO getBySlug(String slug) {
        return industryRepository.findBySlug(slug)
                .map(IndustryMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Industry not found: " + slug));
    }
}
