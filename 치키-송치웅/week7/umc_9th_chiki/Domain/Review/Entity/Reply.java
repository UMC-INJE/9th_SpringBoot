package com.example.umc_9th_chiki.Domain.Review.Entity;

import jakarta.persistence.*;
import lombok.*;
import com.example.umc_9th_chiki.Global.G_Entity.BaseEntity;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "reply")

public class Reply extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reply_id")
    private Long id;

    @Column(name = "content", columnDefinition = "TEXT", nullable = false)
    private String content;

    // Review 엔티티와의 다대일(N:1) 관계 설정
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id", nullable = false)
    private Review review;
}