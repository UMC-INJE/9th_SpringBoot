package com.example.umc9th.domain.member.entity;

import com.example.umc9th.domain.mission.entity.MemberMission;
import com.example.umc9th.domain.review.entity.Review;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import com.example.umc9th.domain.member.enums.*;
import com.example.umc9th.domain.member.entity.mapping.*;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private Gender gender = Gender.NONE;

    @Column(name = "birth")
    private LocalDate birth;

    @Enumerated(EnumType.STRING)
    @Column(name = "address", nullable = false)
    private Address address = Address.NONE;

    @Column(name = "detail_address", nullable = false, length = 100)
    private String detailAddress;

    @Column(name = "social_uid", length = 120)
    private String socialuid;

    @Enumerated(EnumType.STRING)
    @Column(name = "social_type", nullable = false)
    private SocialType socialType;

    @Column(name="point",nullable = false)
    private int point;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name = "phone_number", length = 50)
    private String phoneNumber;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Column(name= "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<MemberFood> memberFoods = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<MemberTerm> memberTerms = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<MemberMission> memberMissions = new ArrayList<>();

    @OneToMany(mappedBy = "review", cascade = CascadeType.REMOVE)
    private List<Review> reviews = new ArrayList<>();
}
