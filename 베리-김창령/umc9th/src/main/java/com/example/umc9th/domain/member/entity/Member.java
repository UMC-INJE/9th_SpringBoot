package com.example.umc9th.domain.member.entity;

import com.example.umc9th.domain.member.enums.SocialType;
import com.example.umc9th.domain.member.enums.Gender;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name="member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name", nullable = false, length = 80)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name="gender")
    private Gender gender;

    @Column(name="birth", nullable = false)
    private LocalDate birth;

    @Column(name="region", nullable = false, length = 100)
    private String region;

    @Column(name="detail_address", length = 255)
    private String detailAddress;

    @Column(name="social_id", nullable = false, length = 100)
    private String socialId;

    @Enumerated(EnumType.STRING)
    @Column(name="social_type",nullable = false, length = 30)
    private SocialType socialType;

    @Column(name="email", nullable = false, length = 100)
    private String email;

    @Column(name="phone_number", nullable = false, length = 11)
    private String phoneNumber;

    @Column(name="deleted_at")
    private LocalDateTime deletedAt;

    @Column(name="created_at", nullable = false, updatable = false)
    private Timestamp createdAt;

    @Column(name="update_at", nullable = false)
    private Timestamp updateAt;
}
