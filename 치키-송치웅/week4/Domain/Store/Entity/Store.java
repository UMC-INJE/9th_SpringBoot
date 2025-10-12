package com.example.umc_9th_chiki.Domain.Store.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;
import com.example.umc_9th_chiki.Domain.Store.Entity.Location;
import com.example.umc_9th_chiki.Global.Auth.G_Entity.BaseEntity;
import com.example.umc_9th_chiki.Domain.Mission.Entity.Mission;
import com.example.umc_9th_chiki.Domain.Review.Entity.Review;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "store")
public class Store extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Long id;

    @Column(name = "name", length = 255, nullable = false)
    private String name;


    @Column(name = "manager_number", length = 255)
    private String managerNumber; // 자바 네이밍 컨벤션에 따라 카멜 케이스 사용


    @Column(name = "detail_address", length = 255, nullable = false)
    private String detailAddress;

    @Column(name = "approval", nullable = false)
    @Builder.Default
    private Boolean approval = false;

    // Location 엔티티와의 다대일(N:1) 관계 설정
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id", nullable = false) // 외래 키 컬럼명 지정
    private Location location;

    @OneToMany(mappedBy = "store")
    private List<Mission> missionList;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<Review> reviewList;
}