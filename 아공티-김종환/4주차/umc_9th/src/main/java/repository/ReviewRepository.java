package repository;

import entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;


public interface ReviewRepository extends JpaRepository<Review, Long> {

    // 2️⃣ 직접 리뷰 작성 (JPQL 또는 Native Query)
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO review_tb (user_id, store_id, content, rating, created_at) " +
                   "VALUES (:userId, :storeId, :content, :rating, CURRENT_TIMESTAMP)", nativeQuery = true)
    int insertReview(@Param("userId") Long userId,
                     @Param("storeId") Long storeId,
                     @Param("content") String content,
                     @Param("rating") Integer rating);
}
