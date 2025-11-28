package com.example.umc9th.domain.food.entity;

import jakarta.persistence.*;
import lombok.*;
import com.example.umc9th.domain.food.enums.FoodName;
import com.example.umc9th.domain.member.entity.mapping.MemberFood;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "food_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private FoodName name;

    @OneToMany(mappedBy = "food", cascade = CascadeType.REMOVE)
    private List<MemberFood> memberFoods = new ArrayList<>();
}

