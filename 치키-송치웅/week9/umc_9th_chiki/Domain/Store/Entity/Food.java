package com.example.umc_9th_chiki.Domain.Store.Entity;

import com.example.umc_9th_chiki.Domain.Member.Enums.FoodName;
import com.example.umc_9th_chiki.Global.G_Entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
@Entity  // 이 클래스가 DB Table과 매핑되는 핵심 객체임을 JPA에게 알리는 것(JPA 엔티티)
@Builder // 객체를 생성할 때 생성자 대신 .builder().필드(값).build()형식으로 간편하게 만들어준다(Lombok:Builder)
@NoArgsConstructor //인수가 없는 기본 생성자 자동 생성(JPA 사용 필수, Lombok:기본생성자)
@AllArgsConstructor //모든 필드를 이누로 받는 전체 생성자 자동 생성(Lombok:전체생성자)
@Getter  // 모든 필드에 대한 Getter 메소드를 자동 생성 (Lombok:Getter)
@Table(name = "food") // 이 엔티티가 DB의 member라는 이름의 테이블과 연결됨을 지정 (테이블 지정)

public class Food extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="food_id")
    private Long id;

    @Column(name = "name",nullable = false)
    @Enumerated(EnumType.STRING)
    private FoodName name;
}
