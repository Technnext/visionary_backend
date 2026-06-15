package com.visionary_backend.repository;

import com.visionary_backend.entity.ServiceBenefit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceBenefitRepository extends JpaRepository<ServiceBenefit, Long> {

    List<ServiceBenefit> findByServiceId(Long serviceId);
}
