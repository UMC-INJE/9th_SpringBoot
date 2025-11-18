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
                .and(review.user.userId.eq(userId)); 

        if (storeName != null && !storeName.isBlank()) {
            where.and(review.store.storeName.containsIgnoreCase(storeName)); 
        }
        if (rating != null) {
            where.and(review.rating.eq(rating));
        }


        List<Review> content = queryFactory
                .selectFrom(review)
                .join(review.store, store).fetchJoin()
                .join(review.user, user).fetchJoin()
                .where(where)
                .orderBy(review.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();


        Long total = queryFactory
                .select(review.count())
                .from(review)
                .join(review.store, store) 
                .join(review.user, user)
                .where(where)
                .fetchOne();

        return new PageImpl<>(content, pageable, total == null ? 0 : total);
    }
}
