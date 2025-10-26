package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import entity.Review;
import java.util.List;

public interface MyPageRepository extends JpaRepository<Review, Integer> {

    List<Review> findByUser_UserIdOrderByCreatedAtDesc(Integer userId);
}
