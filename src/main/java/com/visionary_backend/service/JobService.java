package com.visionary_backend.service;

import com.visionary_backend.dto.JobDTO;
import com.visionary_backend.mapper.JobMapper;
import com.visionary_backend.repository.JobRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {

    private final JobRepository jobRepository;

    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public List<JobDTO> getAll() {
        return JobMapper.toDTOList(jobRepository.findByActiveTrueOrderByPostedDateDesc());
    }

    public List<JobDTO> getByDepartment(String department) {
        return JobMapper.toDTOList(jobRepository.findByDepartmentAndActiveTrue(department));
    }

    public List<JobDTO> getByLocation(String location) {
        return JobMapper.toDTOList(jobRepository.findByLocationAndActiveTrue(location));
    }

    public java.util.Optional<JobDTO> getById(Long id) {
        return jobRepository.findById(id).map(JobMapper::toDTO);
    }
}
