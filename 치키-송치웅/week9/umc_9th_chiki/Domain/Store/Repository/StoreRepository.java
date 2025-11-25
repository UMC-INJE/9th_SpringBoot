package com.example.umc_9th_chiki.Domain.Store.Repository;

import com.example.umc_9th_chiki.Domain.Store.Entity.Store;
import com.example.umc_9th_chiki.Domain.Store.Entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {

    Optional<Store> findByName(String name);

    List<Store> findByLocation(Location location);

    // 특정 가게를 조회하면서 미션 목록(missionList)을 함께 로딩 (Fetch Join)
    @Query("SELECT s FROM Store s JOIN FETCH s.missionList WHERE s.id = :storeId")
    Optional<Store> findByIdWithMissions(@Param("storeId") Long storeId);

    // 특정 가게를 조회하면서 리뷰 목록(reviewList)을 함께 로딩 (Fetch Join)
    @Query("SELECT s FROM Store s JOIN FETCH s.reviewList WHERE s.id = :storeId")
    Optional<Store> findByIdWithReviews(@Param("storeId") Long storeId);

    // 특정 지역(Location)에 속한 모든 가게를 조회하면서, 각 가게의 리뷰 목록도 함께 로딩 (Fetch Join)
    @Query("SELECT s FROM Store s JOIN FETCH s.reviewList WHERE s.location = :location")
    List<Store> findByLocationWithReviews(@Param("location") Location location);
}