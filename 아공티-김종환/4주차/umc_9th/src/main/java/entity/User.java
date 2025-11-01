// entity/User.java
package entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_tb")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "user_name", nullable = false, length = 50)
    private String userName;

    @Column(nullable = false, length = 45)
    private String gender;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(nullable = false, length = 255)
    private String address;

    @Column(name = "detail_address", nullable = false, length = 45)
    private String detailAddress;

    @Column(name = "social_uid", nullable = false, length = 45)
    private String socialUid;

    @Enumerated(EnumType.STRING)
    @Column(name = "social_type", nullable = false, length = 10)
    private SocialType socialType;

    @Column(nullable = false)
    private Integer point;

    @Column(nullable = false, length = 50)
    private String email;

    @Column(length = 45)
    private String phone;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}
