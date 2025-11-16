// src/main/java/repository/ReviewRepositoryImpl.java
package repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import entity.QReview;
import entity.QStore;
import entity.QUser;
import entity.Review;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReviewRepositoryImpl implements ReviewRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public ReviewRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public Page<Review> findMyReviews(Integer userId, String storeName, Integer rating, Pageable pageable) {
        QReview review = QReview.review;
        QStore store = QStore.store;
        QUser user = QUser.user;

        BooleanBuilder where = new BooleanBuilder()
                .and(review.user.userId.eq(userId)); // ← 여기서 user.userId 필드명은 실제 엔티티에 맞게!

        if (storeName != null && !storeName.isBlank()) {
            where.and(review.store.storeName.containsIgnoreCase(storeName)); // 엔티티 필드명과 매칭 필요
        }
        if (rating != null) {
            where.and(review.rating.eq(rating));
        }

        // 내용 조회: 반드시 fetchJoin으로 연관 로딩
        List<Review> content = queryFactory
                .selectFrom(review)
                .join(review.store, store).fetchJoin()
                .join(review.user, user).fetchJoin()
                .where(where)
                .orderBy(review.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        // 총 건수: fetchJoin 금지
        Long total = queryFactory
                .select(review.count())
                .from(review)
                .join(review.store, store) // 조인 조건 동일하게(= where 조건 동일한 결과 수)
                .join(review.user, user)
                .where(where)
                .fetchOne();

        return new PageImpl<>(content, pageable, total == null ? 0 : total);
    }
}
