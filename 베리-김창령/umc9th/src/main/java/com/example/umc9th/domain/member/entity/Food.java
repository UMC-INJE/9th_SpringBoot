package com.example.umc9th.domain.member.entity;

import jakarta.persistence.*;
import lombok.*;
import com.example.umc9th.domain.member.enums.FoodName;
import com.example.umc9th.domain.member.entity.mapping.MemberFood;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "food")
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "food_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name")
    private FoodName name = FoodName.NONE;

    @OneToMany(mappedBy = "food")
    private List<MemberFood> memberFoods = new ArrayList<>();
}
