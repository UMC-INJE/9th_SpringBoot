package com.example.umc_9th_chiki.Domain.Member.Entity.Mapping;

import com.example.umc_9th_chiki.Domain.Member.Entity.Member;
import com.example.umc_9th_chiki.Global.Auth.G_Entity.BaseEntity;
import com.example.umc_9th_chiki.Domain.Member.Entity.Term;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "member_term") // member_term 테이블에 매핑
public class MemberTerm extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Member (N:1 관계)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    // Term (N:1 관계) - Term 엔티티를 참조
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "term_id", nullable = false)
    private Term term;

    // 약관 동의 여부
    @Column(name = "is_agreed", nullable = false)
    @Builder.Default
    private Boolean isAgreed = false;

    // 약관 동의 시점
    @Column(name = "agreed_at")
    private LocalDate agreedAt;
}