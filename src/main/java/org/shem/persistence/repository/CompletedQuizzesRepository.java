package org.shem.persistence.repository;

import org.shem.persistence.entity.CompletedQuizzes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CompletedQuizzesRepository extends JpaRepository<CompletedQuizzes, Integer> {

    @Query("SELECT c FROM CompletedQuizzes c WHERE c.userId = :userId ORDER BY c.completedAt DESC")
    Page<CompletedQuizzes> findByUserId(@Param("userId") Long userId, Pageable pageable);
}

