package com.example.umc_9th_chiki.Domain.Mission.Entity;

import com.example.umc_9th_chiki.Domain.Store.Entity.Store;
import com.example.umc_9th_chiki.Global.Auth.G_Entity.BaseEntity;
import com.example.umc_9th_chiki.Domain.Mission.Entity.Mapping.MemberMission;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Table(name = "mission")
public class Mission extends BaseEntity {

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

    // MemberMission과 1:N 관계 (컬렉션 초기화)
    @OneToMany(mappedBy = "mission", cascade = CascadeType.ALL)
    @Builder.Default
    private List<MemberMission> userMissionList = new ArrayList<>(); // 👈 컬렉션 초기화

    public void updateMission(String conditional, Integer point, LocalDate deadline) {
        this.conditional = conditional;
        this.point = point;
        this.deadline = deadline;
    }
}