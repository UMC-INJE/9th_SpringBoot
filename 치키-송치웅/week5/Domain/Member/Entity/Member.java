package com.example.umc_9th_chiki.Domain.Member.Entity;

import com.example.umc_9th_chiki.Domain.Member.Enums.Gender;
import com.example.umc_9th_chiki.Global.Auth.Enums.SocialType;
import com.example.umc_9th_chiki.Global.Auth.G_Entity.BaseEntity;
import com.example.umc_9th_chiki.Domain.Review.Entity.Review;
import com.example.umc_9th_chiki.Domain.Member.Entity.Mapping.MemberFood;
import com.example.umc_9th_chiki.Domain.Member.Entity.Mapping.MemberTerm;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "member")
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 3, nullable = false)
    private String name;

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Gender gender = Gender.NONE;

    @Column(name = "birth", nullable = false)
    private LocalDate birth;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "detail_address", length = 255)
    private String detailAddress;

    @Column(name = "social_uid", length = 255)
    private String socialUid;

    @Enumerated(EnumType.STRING)
    @Column(name = "social_type", nullable = false)
    private SocialType socialType;

    @Column(name = "point", nullable = false)
    @Builder.Default
    private Integer point = 0;

    @Column(name = "email", length = 255, nullable = false)
    private String email;

    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    // MemberFood와 1:N 매핑
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    @Builder.Default
    private List<MemberFood> memberFoodList = new ArrayList<>();

    // MemberTerm과 1:N 매핑
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    @Builder.Default
    private List<MemberTerm> memberTermList = new ArrayList<>();

    // Review와 1:N 매핑
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Review> reviewList = new ArrayList<>();


    // Dirty Checking을 위한 상태 변경값 저장 함수(주소 변경)
    public void updateAddress(String address, String detailAddress) {
        this.address = address;
        this.detailAddress = detailAddress;
    }
    // Dirty Checking을 위한 상태 변경값 저장 함수(포인트 적립)
    public void addPoint(int changePoint) {
        this.point += changePoint;
    }
}