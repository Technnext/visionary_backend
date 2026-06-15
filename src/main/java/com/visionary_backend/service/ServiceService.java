package com.visionary_backend.service;

import com.visionary_backend.dto.ServiceDTO;
import com.visionary_backend.exception.ResourceNotFoundException;
import com.visionary_backend.mapper.ServiceMapper;
import com.visionary_backend.repository.ServiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceService {

    private final ServiceRepository serviceRepository;

    public ServiceService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public List<ServiceDTO> getAll() {
        return ServiceMapper.toDTOList(serviceRepository.findAllByOrderByDisplayOrderAsc());
    }

    public ServiceDTO getBySlug(String slug) {
        return serviceRepository.findBySlug(slug)
                .map(ServiceMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Service not found: " + slug));
    }
}
