package entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Entity
@Table(
  name = "food_category_tb",
  uniqueConstraints = @UniqueConstraint(name="uk_food_category_name", columnNames = "name")
)
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class FoodCategory {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "food_category_id")
  private Integer id;

  @Column(name="name", nullable=false, length=50)
  private String name;

  // 양방향 필요시
  @OneToMany(mappedBy = "foodCategory", orphanRemoval = false)
  @Builder.Default private List<User> users = new ArrayList<>();
}
