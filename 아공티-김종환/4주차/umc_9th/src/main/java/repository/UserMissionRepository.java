// repository/UserMissionRepository.java
package repository;

import dto.MyMission;
import entity.UserMission;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import java.util.Collection;

public interface UserMissionRepository extends JpaRepository<UserMission, Long> {

  @Query(
    value = """
      select new dto.MyMission(
        m.id,
        s.storeId,
        concat(s.storeName, '에서 ', m.conditional, ' 하세요'),
        concat(m.point, 'p'),
        case 
          when um.status = 'COMPLETED' then '성공'
          when um.status = 'IN_PROGRESS' then '진행중'
          else '기타'
        end,
        m.deadline
      )
      from UserMission um
        join um.mission m
        join m.store s
      where um.user.id = :userId
        and um.status in :statuses
      order by 
        case when um.status = 'COMPLETED' then 0 else 1 end,
        m.deadline asc,
        um.id desc
    """,
    countQuery = """
      select count(um)
      from UserMission um
      where um.user.id = :userId
        and um.status in :statuses
    """
  )
  Page<MyMission> findMyMissionItems(
      @Param("userId") Integer userId,
      @Param("statuses") Collection<String> statuses,
      Pageable pageable
  );

  // 진행중만
  default Page<MyMission> findMyInProgress(Integer userId, Pageable pageable) {
    return findMyMissionItems(userId, java.util.List.of("IN_PROGRESS"), pageable);
  }

  // 완료만
  default Page<MyMission> findMyCompleted(Integer userId, Pageable pageable) {
    return findMyMissionItems(userId, java.util.List.of("COMPLETED"), pageable);
  }
}
