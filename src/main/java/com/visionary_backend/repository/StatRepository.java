package com.visionary_backend.repository;

import com.visionary_backend.entity.Stat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatRepository extends JpaRepository<Stat, Long> {
    List<Stat> findByContextOrderByDisplayOrderAsc(String context);
}
