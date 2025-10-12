package com.example.umc_9th_chiki.Domain.Member.Entity;

import com.example.umc_9th_chiki.Domain.Member.Enums.Gender;
import com.example.umc_9th_chiki.Global.Auth.Enums.SocialType;
import com.example.umc_9th_chiki.Global.Auth.G_Entity.BaseEntity;
import com.example.umc_9th_chiki.Domain.Mission.Entity.Mapping.MemberMission;
import com.example.umc_9th_chiki.Domain.Review.Entity.Review;
import com.example.umc_9th_chiki.Domain.Member.Entity.Mapping.MemberFood;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;
// import는 다른 클래스나 어노테이션의 경로를 지정 (클래스 불러오기)
@Entity  // 이 클래스가 DB Table과 매핑되는 핵심 객체임을 JPA에게 알리는 것(JPA 엔티티)
@Builder // 객체를 생성할 때 생성자 대신 .builder().필드(값).build()형식으로 간편하게 만들어준다(Lombok:Builder)
@NoArgsConstructor //인수가 없는 기본 생성자 자동 생성(JPA 사용 필수, Lombok:기본생성자)
@AllArgsConstructor //모든 필드를 이누로 받는 전체 생성자 자동 생성(Lombok:전체생성자)
@Getter  // 모든 필드에 대한 Getter 메소드를 자동 생성 (Lombok:Getter)
@Table(name = "member") // 이 엔티티가 DB의 member라는 이름의 테이블과 연결됨을 지정 (테이블 지정)
public class Member extends BaseEntity {

    @Id // 이 필드가 테이블의 PK임을 지정.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //AUTO_INCREMENT 기능을 사용하여 자동으로 1씩 증가하는 값을 부여하도록 지정
    private Long id; //필드

    @Column(name = "name", length = 3, nullable = false) //이 필드가 DB table의 name 컬럼에 매핑됨을 지정.
    private String name; //필드

    @Column(name = "gender", nullable = false) //이 필드가 DB table의 gender 컬럼에 매핑됨을 지정.
    @Enumerated(EnumType.STRING)
    // java의 Enum 타입을 DB에 저장할 때, 숫자가 아닌 문자열로 저장 지정.
    @Builder.Default // 초기값 지정.(테이블 위에 있어야 함)
    private Gender gender = Gender.NONE; //@Builder.Default 사용 시 초기 값 할당 해야함.

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
    @Builder.Default // DEFAULT 0 설정을 위해 Builder.Default 사용
    private Integer point = 0;

    @Column(name = "email", length = 255, nullable = false)
    private String email;

    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    //UserFood와 1:N 매핑
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberFood> memberFoodList;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberMission> memberMissionList;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Review> reviewList;
}
