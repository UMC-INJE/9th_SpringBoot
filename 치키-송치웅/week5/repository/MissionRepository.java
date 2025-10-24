package com.example.umc_9th_chiki.repository;

import com.example.umc_9th_chiki.Domain.Mission.Entity.Mission;
import com.example.umc_9th_chiki.Domain.Store.Entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface MissionRepository extends JpaRepository<Mission, Long> {

    // 특정 포인트 이상의 미션 목록 조회
    @Query("SELECT m FROM Mission m WHERE m.point >= :minPoint")
    List<Mission> findMissionsWithMinPoint(@Param("minPoint") Integer minPoint);

    // 마감일이 특정 날짜 이전인 미션 목록 조회 (종료된 미션 찾기)
    @Query("SELECT m FROM Mission m WHERE m.deadline < :date")
    List<Mission> findExpiredMissions(@Param("date") LocalDate date);

    // 특정 가게(Store)에 속한 모든 미션 목록 조회 (Store와 Fetch Join)
    @Query("SELECT m FROM Mission m JOIN FETCH m.store s WHERE s.id = :storeId")
    List<Mission> findByStoreIdWithStore(@Param("storeId") Long storeId);

    // 미션 ID로 조회하면서 해당 미션을 수행 중인 MemberMission 목록도 함께 조회 (Fetch Join)
    @Query("SELECT m FROM Mission m JOIN FETCH m.userMissionList WHERE m.id = :missionId")
    Optional<Mission> findByIdWithMemberMissions(@Param("missionId") Long missionId);

    // 특정 Store 엔티티에 속한 모든 미션 조회 (Store 객체를 파라미터로 받음)
    List<Mission> findByStore(Store store);
}