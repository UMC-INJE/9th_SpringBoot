package entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "mission_tb")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Mission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mission_id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "store_id", foreignKey = @ForeignKey(name = "fk_mission_store"))
    private Store store;

    @Column(name = "conditional", nullable = false, length = 100)
    private String conditional;

    @Column(name = "point", nullable = false)
    private Integer point;

    @Column(name = "deadline", nullable = false)
    private LocalDate deadline; // MySQL DATE → Java LocalDate

    @Column(name = "created_at", nullable = false)
    private Integer createdAt; // DB에서 INT로 관리되는 created_at

    @OneToMany(mappedBy = "mission")
    @Builder.Default
    private List<UserMission> userMissions = new ArrayList<>();
}
