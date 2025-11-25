package repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import entity.UserMission;

public interface UserMissionRepository extends JpaRepository<UserMission, Integer> {

    
    Page<UserMission> findByUser_UserIdAndStatus(Integer userId, String status, Pageable pageable);
    boolean existsByUser_UserIdAndMission_IdAndStatus(Integer userId,Integer missionId,String status);
    
}
