package entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(
    name = "mission_tb"
    // 만약 store_id가 UNIQUE(가게당 미션 1개)라면 아래 주석 해제
    // , uniqueConstraints = @UniqueConstraint(name = "uk_mission_store", columnNames = "store_id")
)
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Mission {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "mission_id")
  private Integer id;

  // FK: store_id INT NN  (MySQL에서 UNSIGNED가 필요하면 columnDefinition 사용)
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(
      name = "store_id",
      nullable = false,
      foreignKey = @ForeignKey(name = "fk_mission_store")
      // , columnDefinition = "INT UNSIGNED"  // 만약 UN = UNSIGNED 의미라면 이 줄을 사용
  )
  private Store store;

  // deadline DATE NN
  @Column(name = "deadline", nullable = false)
  private LocalDate deadline;

  // conditional VARCHAR(100) NN
  @Column(name = "conditional", nullable = false, length = 100)
  private String conditional;

  // point INT NN
  @Column(name = "point", nullable = false)
  private Integer point;

  // created_at INT NN  (epoch seconds 등 정수 저장 가정)
  @Column(name = "created_at", nullable = false)
  private Integer createdAt;

  @OneToMany(mappedBy = "mission")
  @Builder.Default
  private List<UserMission> userMissions = new ArrayList<>();
}
