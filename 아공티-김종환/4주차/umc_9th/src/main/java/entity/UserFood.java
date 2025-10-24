package entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
    name = "user_food_tb"
    // 만약 user_id가 UNIQUE(사용자당 딱 1행)라면 아래 주석 해제
    // , uniqueConstraints = @UniqueConstraint(name = "uk_user_food_user", columnNames = "user_id")
)
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class UserFood {

  // user_food_id INT PK NN AI
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_food_id")
  private Integer id;

  // user_id INT NN UN (UNIQUE/UNSIGNED 모호 → 기본안: 제약 미부여, 필요 시 위 uniqueConstraints 사용)
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(
      name = "user_id",
      nullable = false,
      foreignKey = @ForeignKey(name = "fk_user_food_user")
      // , columnDefinition = "INT UNSIGNED" // UN이 UNSIGNED 의미라면 주석 해제
  )
  private User user;

  // food_category_id VARCHAR(45) NN  → FoodCategory.name(UNIQUE) 기준으로 FK 매핑
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(
      name = "food_category_id",            // 로컬 컬럼명 (VARCHAR(45))
      referencedColumnName = "name",        // 타겟 컬럼(FoodCategory.name)
      nullable = false,
      foreignKey = @ForeignKey(name = "fk_user_food_category_by_name")
  )
  private FoodCategory foodCategory;
}
