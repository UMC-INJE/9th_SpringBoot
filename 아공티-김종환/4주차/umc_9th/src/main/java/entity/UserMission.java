package entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.*;

@Entity
@Table(name="user_mission_tb",
  uniqueConstraints = {
    @UniqueConstraint(name="uk_user_mission", columnNames={"user_id","mission_id"})
  }
)
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class UserMission {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch=FetchType.LAZY, optional=false)
  @JoinColumn(name="user_id", foreignKey=@ForeignKey(name="fk_user_mission_user"))
  private User user;

  @ManyToOne(fetch=FetchType.LAZY, optional=false)
  @JoinColumn(name="mission_id", foreignKey=@ForeignKey(name="fk_user_mission_mission"))
  private Mission mission;

  @Column(name="status", nullable=false, length=100)
  private String status;


}
