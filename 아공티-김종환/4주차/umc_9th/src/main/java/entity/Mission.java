package entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Entity
@Table(name="mission_tb")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Mission {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="mission_id")
  private Integer id;

  @ManyToOne(fetch=FetchType.LAZY, optional=false)
  @JoinColumn(name="store_id", foreignKey=@ForeignKey(name="fk_mission_store"))
  private Store store;

  @Column(name="mission_name", nullable=false, length=100)
  private String missionName;

  @Column(name="condition_text", columnDefinition="TEXT")
  private String conditionText;

  @Column(name="reawrd_point", nullable=false) // ← 오타 반영
  private Integer rewardPoint;

  @OneToMany(mappedBy="mission")
  @Builder.Default private List<UserMission> userMissions = new ArrayList<>();
}
