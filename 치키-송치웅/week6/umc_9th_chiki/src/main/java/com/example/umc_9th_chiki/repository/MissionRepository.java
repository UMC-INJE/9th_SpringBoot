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

    @Query("SELECT m FROM Mission m WHERE m.point >= :minPoint")
    List<Mission> findMissionsWithMinPoint(@Param("minPoint") Integer minPoint);

    @Query("SELECT m FROM Mission m WHERE m.deadline < :date")
    List<Mission> findExpiredMissions(@Param("date") LocalDate date);

    @Query("SELECT m FROM Mission m JOIN FETCH m.store s WHERE s.id = :storeId")
    List<Mission> findByStoreIdWithStore(@Param("storeId") Long storeId);

    @Query("SELECT m FROM Mission m JOIN FETCH m.userMissionList WHERE m.id = :missionId")
    Optional<Mission> findByIdWithMemberMissions(@Param("missionId") Long missionId);

    List<Mission> findByStore(Store store);
}