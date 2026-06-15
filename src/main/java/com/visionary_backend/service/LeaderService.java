package com.visionary_backend.service;

import com.visionary_backend.entity.Leader;
import com.visionary_backend.repository.LeaderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaderService {

    private final LeaderRepository leaderRepository;

    public LeaderService(LeaderRepository leaderRepository) {
        this.leaderRepository = leaderRepository;
    }

    public List<Leader> getAll() {
        return leaderRepository.findAllByOrderByDisplayOrderAsc();
    }
}
