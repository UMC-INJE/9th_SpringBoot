package entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_tb")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;

    @Column(nullable = false, length = 50)
    private String email;

    @Column(nullable = false, length = 45)
    private String password;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 45)
    private String gender;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(length = 255)
    private String address;

    @Column(name = "food_category_id")
    private Integer foodCategoryId;

    @Column(length = 45)
    private String phone;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    private Integer point;
}
