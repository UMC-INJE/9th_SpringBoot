package com.example.umc_9th_chiki.Domain.Store.Entity;

import com.example.umc_9th_chiki.Global.Auth.G_Entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "location")

public class Location extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "location_id")
    private Long id;

    @Column(name = "name", length = 255, nullable = false)
    private String name;

    @OneToMany(mappedBy = "location")
    private List<Store> storeList;
}
