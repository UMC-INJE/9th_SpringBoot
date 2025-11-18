package entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Entity
@Table(name = "food_category_tb")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class FoodCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "food_category_id")
    private Integer id;

    @Column(name = "name", nullable = false, unique = true, length = 100)
    private String name;


    @OneToMany(mappedBy = "foodCategory")
    @Builder.Default
    private List<User> users = new ArrayList<>();
}
