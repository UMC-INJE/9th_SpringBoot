package entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.*;

@Entity
@Table(
    name = "user_mission_tb",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "uk_user_mission",
            columnNames = {"user_id", "mission_id"}
        )
    }
)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserMission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_mission_id") // ✅ DB 실제 컬럼명과 정확히 일치시킴
    private Integer userMissionId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
        name = "user_id",
        foreignKey = @ForeignKey(name = "fk_user_mission_user")
    )
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
        name = "mission_id",
        foreignKey = @ForeignKey(name = "fk_user_mission_mission")
    )
    private Mission mission;

    @Column(name = "status", nullable = false, length = 100)
    private String status;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
