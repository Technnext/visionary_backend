package com.visionary_backend.repository;

import com.visionary_backend.entity.Insight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InsightRepository extends JpaRepository<Insight, Long> {

    Optional<Insight> findBySlug(String slug);

    List<Insight> findByIsFeaturedTrue();

    List<Insight> findByCategory(String category);

    List<Insight> findAllByOrderByPublishedDateDesc();

    @Query("SELECT DISTINCT i.category FROM Insight i WHERE i.category IS NOT NULL ORDER BY i.category")
    List<String> findDistinctCategories();
}
