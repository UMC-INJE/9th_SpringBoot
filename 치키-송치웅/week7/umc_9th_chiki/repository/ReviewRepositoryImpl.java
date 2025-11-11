package com.example.umc_9th_chiki.repository;

import com.example.umc_9th_chiki.Domain.Review.Entity.QReview;
import com.example.umc_9th_chiki.Domain.Review.Entity.Review;
import com.example.umc_9th_chiki.Domain.Store.Entity.QStore;
import com.example.umc_9th_chiki.Domain.Store.Entity.QLocation;
import com.example.umc_9th_chiki.Domain.Member.Entity.QMember; // QMember import
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewQueryDsl {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Review> searchReview(Predicate predicate) {
        QReview review = QReview.review;
        QStore store = QStore.store;
        QLocation location = QLocation.location;

        return queryFactory
                .selectFrom(review)
                .join(review.store, store).fetchJoin()
                .join(store.location, location).fetchJoin()
                .where(predicate)
                .fetch();
    }

    @Override
    public List<Review> searchReviewByLocation(String locationName) {
        QReview review = QReview.review;
        QStore store = QStore.store;
        QLocation location = QLocation.location;

        return queryFactory
                .selectFrom(review)
                .join(review.store, store).fetchJoin()
                .join(store.location, location).fetchJoin()
                .where(location.name.contains(locationName))
                .fetch();
    }

    @Override
    public List<Review> findReviewsByConditions(Predicate predicate) {
        QReview review = QReview.review;
        QStore store = QStore.store;
        QMember member = QMember.member; // QMember 선언

        return queryFactory
                .selectFrom(review)
                .join(review.store, store).fetchJoin() // fetchJoin 복구
                .join(review.member, member).fetchJoin() // fetchJoin 복구
                .where(predicate)
                .orderBy(review.createdAt.desc())
                .fetch();
    }
}