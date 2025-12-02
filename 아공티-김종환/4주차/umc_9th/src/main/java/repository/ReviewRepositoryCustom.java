package repository;

import entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewRepositoryCustom {
    
    Page<Review> findMyReviews(Integer userId, String storeName, Integer rating, Pageable pageable);
}
