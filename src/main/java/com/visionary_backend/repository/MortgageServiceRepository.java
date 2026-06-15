package com.visionary_backend.repository;

import com.visionary_backend.entity.MortgageService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MortgageServiceRepository extends JpaRepository<MortgageService, Long> {

    Optional<MortgageService> findBySlug(String slug);

    List<MortgageService> findAllByOrderByDisplayOrderAsc();
}
