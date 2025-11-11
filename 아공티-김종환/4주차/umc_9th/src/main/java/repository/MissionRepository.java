package repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import entity.Mission;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MissionRepository extends JpaRepository<Mission, Integer> {

    // 현재 지역(regionId)에 해당하는 미션 목록 (도전 가능한 미션)
    @Query("""
        SELECT m FROM Mission m
        JOIN m.store s
        JOIN s.region r
        WHERE r.id = :regionId
        ORDER BY m.deadline ASC
    """)
    Page<Mission> findAvailableMissionsByRegion(@Param("regionId") Integer regionId, Pageable pageable);
}
