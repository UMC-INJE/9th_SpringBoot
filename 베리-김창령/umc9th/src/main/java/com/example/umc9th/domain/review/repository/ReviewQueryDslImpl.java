package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.review.entity.QReview;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.store.entity.QLocation;
import com.example.umc9th.domain.store.entity.QStore;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RequiredArgsConstructor
public class ReviewQueryDslImpl implements ReviewQueryDsl {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Review> searchReview(BooleanBuilder builder, Pageable pageable) {

        QReview review = QReview.review;
        QStore store = QStore.store;
        QLocation location = QLocation.location;

        List<Review> content = queryFactory
                .selectFrom(review)
                .leftJoin(review.store, store).fetchJoin()
                .leftJoin(store.location, location).fetchJoin()
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = queryFactory
                .select(review.count())
                .from(review)
                .leftJoin(review.store, store)
                .leftJoin(store.location, location)
                .where(builder)
                .fetchOne();

        return new PageImpl<>(content, pageable, total);
    }
}
