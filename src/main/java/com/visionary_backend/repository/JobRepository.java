package com.visionary_backend.repository;

import com.visionary_backend.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {

    List<Job> findByActiveTrue();

    List<Job> findByDepartmentAndActiveTrue(String department);

    List<Job> findByLocationAndActiveTrue(String location);

    List<Job> findByActiveTrueOrderByPostedDateDesc();
}
