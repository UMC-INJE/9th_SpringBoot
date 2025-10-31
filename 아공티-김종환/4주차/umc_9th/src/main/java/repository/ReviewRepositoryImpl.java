// src/main/java/repository/ReviewRepositoryImpl.java
package repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import entity.QReview;
import entity.Review;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReviewRepositoryImpl implements ReviewRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    // 명시적 생성자 주입 
    public ReviewRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public Page<Review> findMyReviews(Integer userId, String storeName, Integer rating, Pageable pageable) {
        QReview review = QReview.review;

        BooleanBuilder where = new BooleanBuilder()
                // 내가 작성한 리뷰
                .and(review.user.userId.eq(userId));

        // 가게별 필터 
        if (storeName != null && !storeName.isBlank()) {
            where.and(review.store.storeName.containsIgnoreCase(storeName));
        }

        // 별점별 필터 (선택, 1~5 정수)
        if (rating != null) {
            where.and(review.rating.eq(rating));
            
        }

        // 내용 조회 (최신순 + 페이징)
        List<Review> content = queryFactory
                .selectFrom(review)
                .where(where)
                .orderBy(review.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        // 총 건수 (count 쿼리 분리)
        Long total = queryFactory
                .select(review.count())
                .from(review)
                .where(where)
                .fetchOne();

        return new PageImpl<>(content, pageable, total == null ? 0 : total);
    }
}
