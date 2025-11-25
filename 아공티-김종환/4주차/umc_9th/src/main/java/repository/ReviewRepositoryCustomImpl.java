package repository;

import entity.Review;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewRepositoryCustomImpl implements ReviewRepositoryCustom {

    private final EntityManager em;

    @Override
    public Page<Review> findMyReviews(
            Integer userId,
            String storeName,
            Integer rating,
            Pageable pageable
    ) {
        //메인 조회 쿼리
        StringBuilder jpql = new StringBuilder(
                "SELECT r FROM Review r " +
                "JOIN r.store s " +
                "WHERE r.user.id = :userId"
        );

        if (storeName != null) {
            jpql.append(" AND s.name LIKE :storeName");
        }
        if (rating != null) {
            jpql.append(" AND r.rating = :rating");
        }

        var query = em.createQuery(jpql.toString(), Review.class)
                .setParameter("userId", userId);

        if (storeName != null) {
            query.setParameter("storeName", "%" + storeName + "%");
        }
        if (rating != null) {
            query.setParameter("rating", rating);
        }

        //전체 개수 쿼리
        StringBuilder countJpql = new StringBuilder(
                "SELECT COUNT(r) FROM Review r " +
                "JOIN r.store s " +
                "WHERE r.user.id = :userId"
        );

        if (storeName != null) {
            countJpql.append(" AND s.name LIKE :storeName");
        }
        if (rating != null) {
            countJpql.append(" AND r.rating = :rating");
        }

        var countQuery = em.createQuery(countJpql.toString(), Long.class)
                .setParameter("userId", userId);

        if (storeName != null) {
            countQuery.setParameter("storeName", "%" + storeName + "%");
        }
        if (rating != null) {
            countQuery.setParameter("rating", rating);
        }

        Long total = countQuery.getSingleResult();

        //페이지 내용 조회
        List<Review> content = query
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();

        return new PageImpl<>(content, pageable, total);
    }
}
