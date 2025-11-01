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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Mission> missionList = new ArrayList<>();

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Review> reviewList = new ArrayList<>();

    // 가게 정보 수정 (JPA 변경 감지 대상)
    public void updateInfo(String managerNumber, String detailAddress) {
        this.managerNumber = managerNumber;
        this.detailAddress = detailAddress;
    }
}