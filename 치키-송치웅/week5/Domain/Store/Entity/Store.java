package com.example.umc_9th_chiki.Domain.Store.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;
import java.util.ArrayList;
import com.example.umc_9th_chiki.Domain.Store.Entity.Location;
import com.example.umc_9th_chiki.Global.Auth.G_Entity.BaseEntity;
import com.example.umc_9th_chiki.Domain.Mission.Entity.Mission;
import com.example.umc_9th_chiki.Domain.Review.Entity.Review;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
    private String managerNumber;

    @Column(name = "detail_address", length = 255, nullable = false)
    private String detailAddress;


    // Location 엔티티와의 다대일(N:1) 관계 설정
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    // Mission과 1:N 관계 (컬렉션 초기화, Cascade 추가)
    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Mission> missionList = new ArrayList<>();

    // Review와 1:N 관계 (컬렉션 초기화)
    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Review> reviewList = new ArrayList<>();


    // Dirty Checking을 위한 상태 변경값 저장 함수(가게 정보 변경)
    public void updateInfo(String managerNumber, String detailAddress) {
        this.managerNumber = managerNumber;
        this.detailAddress = detailAddress;
    }
}