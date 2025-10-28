package repository;

import entity.Review;
import java.util.List;

public interface ReviewRepositoryCustom {
    List<Review> findMyReviews(Long userId, String storeName, Integer rating);
}
