// src/main/java/com/example/umc9th/domain/review/repository/ReviewRepository.java
package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewQueryDsl {
    List<Review> findByStore_IdOrderByCreatedAtDesc(Long storeId);
}
