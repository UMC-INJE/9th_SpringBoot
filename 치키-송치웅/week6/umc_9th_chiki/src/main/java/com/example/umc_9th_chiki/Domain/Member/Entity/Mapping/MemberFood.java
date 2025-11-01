package com.example.umc_9th_chiki.Domain.Member.Entity.Mapping;

import com.example.umc_9th_chiki.Domain.Store.Entity.Food;
import com.example.umc_9th_chiki.Domain.Member.Entity.Member;
import com.example.umc_9th_chiki.Global.Auth.G_Entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity  // 이 클래스가 DB Table과 매핑되는 핵심 객체임을 JPA에게 알리는 것(JPA 엔티티)
@Builder // 객체를 생성할 때 생성자 대신 .builder().필드(값).build()형식으로 간편하게 만들어준다(Lombok:Builder)
@NoArgsConstructor //인수가 없는 기본 생성자 자동 생성(JPA 사용 필수, Lombok:기본생성자)
@AllArgsConstructor //모든 필드를 이누로 받는 전체 생성자 자동 생성(Lombok:전체생성자)
@Getter  // 모든 필드에 대한 Getter 메소드를 자동 생성 (Lombok:Getter)
@Table(name = "member_food") // 이 엔티티가 DB의 member라는 이름의 테이블과 연결됨을 지정 (테이블 지정)

public class MemberFood extends BaseEntity{

    @Id // 이 필드가 테이블의 PK임을 지정.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //AUTO_INCREMENT 기능을 사용하여 자동으로 1씩 증가하는 값을 부여하도록 지정
    private Long id; //필드

    @ManyToOne(fetch = FetchType.LAZY) // ManyToOne은 1:N 관계에서 1임을 정의 ,fetch(즉시 로딩 || 지연 로딩)
    @JoinColumn(name = "member_id") //JoinColumn은 FK의 주인을 설정.
    private Member member; //필드

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_id", nullable = false) // 외래 키 컬럼명 지정
    private Food food;

}