package com.visionary_backend.repository;

import com.visionary_backend.entity.NavigationLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NavigationLinkRepository extends JpaRepository<NavigationLink, Long> {

    List<NavigationLink> findBySectionAndIsActiveTrueOrderByDisplayOrderAsc(String section);

    List<NavigationLink> findByIsActiveTrueOrderBySectionAscDisplayOrderAsc();
}
