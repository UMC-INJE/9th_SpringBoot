package com.example.umc_9th_chiki.Domain.Store.Entity;

import com.example.umc_9th_chiki.Domain.Mission.Entity.Mission;
import com.example.umc_9th_chiki.Domain.Review.Entity.Review;
import com.example.umc_9th_chiki.Global.G_Entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "store")
public class Store extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(length = 50)
    private String category;

    @Column(name = "address", nullable = false, length = 50)
    private String address;         // 기본 주소

    @Column(name = "detail_address", length = 50)
    private String detailAddress;   // 상세 주소

    @Column(name = "manager_number", length = 50)
    private String managerNumber;   // [재추가] 사업자 번호? 관리자 번호?

    private Float score;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Location location;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Mission> missionList = new ArrayList<>();

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Review> reviewList = new ArrayList<>();

    // 정보 수정 메서드
    public void updateInfo(String managerNumber, String detailAddress, String address) {
        this.managerNumber = managerNumber;
        this.detailAddress = detailAddress;
        this.address = address;
    }
}