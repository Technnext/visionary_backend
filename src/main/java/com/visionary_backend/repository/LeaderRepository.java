package com.visionary_backend.repository;

import com.visionary_backend.entity.Leader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaderRepository extends JpaRepository<Leader, Long> {

    List<Leader> findAllByOrderByDisplayOrderAsc();
}
