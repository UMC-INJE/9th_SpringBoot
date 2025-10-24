// repository/MissionRepository.java
package repository;


import entity.Mission;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import dto.MissionSummary;

public interface MissionRepository extends JpaRepository<Mission, Integer> {

  /**
   * 홈 화면: 선택 지역의 '도전 가능' 미션 목록 (페이징)
   * 조건:
   *  - m.deadline >= CURRENT_DATE (마감 안 지난 것)
   *  - 사용자(:userId)가 COMPLETED한 적 없는 미션
   *  - (옵션) 진행중(IN_PROGRESS)도 제외하려면 주석 해제
   * 표시:
   *  - D-Day: function('datediff', m.deadline, CURRENT_DATE)
   *  - subtitle: concat(m.conditional, ' ', m.point, 'P 적립')
   */
  @Query(
    value = """
      select new dto.HomeMissionCard(
        m.id,
        s.storeId,
        s.storeName,
        function('datediff', m.deadline, CURRENT_DATE),
        /* Store에 카테고리 컬럼이 생기면 여기 교체: s.categoryName 등 */
        null,
        concat(m.conditional, ' ', m.point, 'P 적립'),
        m.deadline
      )
      from Mission m
        join m.store s
        join s.region r
      where r.id = :regionId
        and m.deadline >= CURRENT_DATE
        and not exists (
          select 1 from UserMission um
           where um.mission.id = m.id
             and um.user.id = :userId
             and um.status = 'COMPLETED'
        )
        /* 진행중 제외까지 원하면 해제
        and not exists (
          select 1 from UserMission um2
           where um2.mission.id = m.id
             and um2.user.id = :userId
             and um2.status = 'IN_PROGRESS'
        )
        */
      order by m.deadline asc, m.id desc
    """,
    countQuery = """
      select count(m)
      from Mission m
        join m.store s
        join s.region r
      where r.id = :regionId
        and m.deadline >= CURRENT_DATE
        and not exists (
          select 1 from UserMission um
           where um.mission.id = m.id
             and um.user.id = :userId
             and um.status = 'COMPLETED'
        )
    """
  )
  Page<dto.Mission> findHomeMissions(
      @Param("regionId") Integer regionId,
      @Param("userId") Integer userId,
      Pageable pageable
  );

  /**
   * 상단 요약: 도전 가능 개수 + 총 포인트
   * (위 목록 쿼리의 조건과 동일)
   */
  @Query("""
    select new dto.HomeSummary(
      count(m),
      coalesce(sum(m.point), 0)
    )
    from Mission m
      join m.store s
      join s.region r
    where r.id = :regionId
      and m.deadline >= CURRENT_DATE
      and not exists (
        select 1 from UserMission um
         where um.mission.id = m.id
           and um.user.id = :userId
           and um.status = 'COMPLETED'
      )
  """)
  MissionSummary findHomeSummary(@Param("regionId") Integer regionId,
                              @Param("userId") Integer userId);
}
