package com.visionary_backend.service;

import com.visionary_backend.dto.MortgageServiceDTO;
import com.visionary_backend.exception.ResourceNotFoundException;
import com.visionary_backend.mapper.MortgageServiceMapper;
import com.visionary_backend.repository.MortgageServiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MortgageServiceService {

    private final MortgageServiceRepository mortgageServiceRepository;

    public MortgageServiceService(MortgageServiceRepository mortgageServiceRepository) {
        this.mortgageServiceRepository = mortgageServiceRepository;
    }

    public List<MortgageServiceDTO> getAll() {
        return MortgageServiceMapper.toDTOList(mortgageServiceRepository.findAllByOrderByDisplayOrderAsc());
    }

    public MortgageServiceDTO getBySlug(String slug) {
        return mortgageServiceRepository.findBySlug(slug)
                .map(MortgageServiceMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Mortgage service not found: " + slug));
    }
}
