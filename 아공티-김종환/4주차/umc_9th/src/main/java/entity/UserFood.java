package entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_food_tb")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserFood {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_food_id")
    private Integer userFoodId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_userfood_user"))
    private User user;

    @Column(name = "food_category_id", nullable = false, length = 45)
    private String foodCategoryId;
}
