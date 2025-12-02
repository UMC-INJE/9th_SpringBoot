package com.example.umc9th.domain.mission.entity;

import com.example.umc9th.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class MemberMission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_mission_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id", nullable = false)
    private Mission mission;

    @Column(name = "is_complete", nullable = false)
    private Boolean isComplete = false;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public void complete() {
        this.isComplete = true;
    }

    public Boolean getIsComplete() {
        return this.isComplete;
    }
}
