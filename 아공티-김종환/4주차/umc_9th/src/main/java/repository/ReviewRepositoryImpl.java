package repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.core.BooleanBuilder;
import entity.QReview;
import entity.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Review> findMyReviews(Long userId, String storeName, Integer rating) {
        QReview review = QReview.review;
        BooleanBuilder builder = new BooleanBuilder();

        // 내가 작성한 리뷰만
        builder.and(review.user.userId.eq(userId));

        // 가게명 필터 (optional)
        if (storeName != null && !storeName.isEmpty()) {
            builder.and(review.store.storeName.containsIgnoreCase(storeName));
        }

        // 별점 필터 (optional)
        if (rating != null) {
            builder.and(review.rating.eq(rating));
        }

        // QueryDSL 쿼리 실행
        return queryFactory
                .selectFrom(review)
                .where(builder)
                .orderBy(review.createdAt.desc())
                .fetch();
    }
}
