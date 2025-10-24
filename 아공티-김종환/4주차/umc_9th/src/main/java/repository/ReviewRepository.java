package repository;

import entity.Review;
import java.util.Optional;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

  // 가게 리뷰 목록 (최신순)
  Page<Review> findByStore_IdOrderByCreatedAtDesc(Integer storeId, Pageable pageable);

  // 사용자-가게 중복 여부
  @Query("select (count(r) > 0) from Review r where r.user.id = :userId and r.store.id = :storeId")
  boolean existsByUserAndStore(@Param("userId") Integer userId, @Param("storeId") Integer storeId);

  // 가게 평균 별점 + 개수
  @Query("""
    select coalesce(avg(r.rating), 0), count(r)
    from Review r
    where r.store.id = :storeId
  """)
  Object[] findAvgAndCountByStoreId(@Param("storeId") Integer storeId);

  // 내가 쓴 특정 가게 리뷰 찾아서 수정 시 사용
  Optional<Review> findByUser_IdAndStore_Id(Integer userId, Integer storeId);
}
