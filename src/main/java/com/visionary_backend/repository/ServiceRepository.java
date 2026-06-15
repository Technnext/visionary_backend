package com.visionary_backend.repository;

import com.visionary_backend.entity.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ServiceRepository extends JpaRepository<ServiceEntity, Long> {

    Optional<ServiceEntity> findBySlug(String slug);

    List<ServiceEntity> findAllByOrderByDisplayOrderAsc();
}
