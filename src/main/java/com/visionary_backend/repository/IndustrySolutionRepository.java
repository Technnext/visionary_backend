package com.visionary_backend.repository;

import com.visionary_backend.entity.IndustrySolution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IndustrySolutionRepository extends JpaRepository<IndustrySolution, Long> {

    List<IndustrySolution> findByIndustryId(Long industryId);
}
