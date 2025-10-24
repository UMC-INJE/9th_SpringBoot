// repository/UserRepository.java
package repository;

import dto.MyPage;
import entity.User;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Integer> {

  @Query("""
     select new dto.MyPageView(
        u.userName,     -- 닉네임으로 사용
        u.email,
        u.phone,
        u.point
     )
     from User u
     where u.id = :userId
  """)
  MyPage findMyPageView(@Param("userId") Integer userId);
}
