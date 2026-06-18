package com.visionary_backend.repository;

import com.visionary_backend.entity.HeroSection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HeroSectionRepository extends JpaRepository<HeroSection, Long> {

    Optional<HeroSection> findByPageKeyAndIsActiveTrue(String pageKey);

    List<HeroSection> findByIsActiveTrueOrderByDisplayOrderAsc();
}
