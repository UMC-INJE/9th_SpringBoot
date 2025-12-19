package com.example.umc9th.domain.mission.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.example.umc9th.global.auth.entity.BaseEntity;
import com.example.umc9th.domain.store.entity.Store;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Mission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mission_id")
    private Long id;

    @Column(nullable = false)
    private LocalDate deadline;

    @Column(nullable = false)
    private String conditional;

    @Column(nullable = false)
    private int point;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @Builder.Default
    @OneToMany(mappedBy = "mission", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<MemberMission> memberMissions = new ArrayList<>();

    public void setStore(Store store) {
        this.store = store;
    }
}


