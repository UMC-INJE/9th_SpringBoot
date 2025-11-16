package com.example.umc_9th_chiki.Domain.Mission.Entity.Mapping;

import com.example.umc_9th_chiki.Domain.Member.Entity.Member;
import com.example.umc_9th_chiki.Domain.Mission.Entity.Mission;
import com.example.umc_9th_chiki.Global.G_Entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "user_mission")
public class MemberMission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_mission_id")
    private Long id;

    @Column(name = "is_complete", nullable = false)
    @Builder.Default // Lombok Builder 사용 시 기본값 설정
    private Boolean isComplete = false;

    // Mission 엔티티와의 다대일(N:1) 관계 설정
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id", nullable = false)
    private Mission mission;

    // Member 엔티티와의 다대일(N:1) 관계 설정
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private Member member;
}