package com.example.umc_9th_chiki.Domain.Mission.Entity;

import com.example.umc_9th_chiki.Domain.Store.Entity.Store;
import com.example.umc_9th_chiki.Global.Auth.G_Entity.BaseEntity;
import com.example.umc_9th_chiki.Domain.Mission.Entity.Mapping.MemberMission;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "mission")
public class Mission extends BaseEntity { // BaseEntity를 상속받아 Auditing 필드를 관리

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mission_id")
    private Long id;

    @Column(name = "deadline")
    private LocalDate deadline;

    @Column(name = "conditional", columnDefinition = "TEXT")
    private String conditional;

    @Column(name = "point", nullable = false)
    private Integer point;

    // Store 엔티티와의 다대일(N:1) 관계 설정
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @OneToMany(mappedBy = "mission", cascade = CascadeType.ALL)
    private List<MemberMission> userMissionList;
}