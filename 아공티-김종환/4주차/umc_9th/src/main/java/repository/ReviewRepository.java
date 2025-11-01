// repository/ReviewRepository.java
package repository;

import entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Integer>, ReviewRepositoryCustom {
}
