package entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Entity
@Table(
    name = "store_tb"
    // 만약 region_id가 UNIQUE(지역당 매장 1개) 조건이라면 아래 주석 해제
     , uniqueConstraints = @UniqueConstraint(name = "uk_store_region", columnNames = "region_id")
)
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Store {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "stroe_id") // DB 오타 그대로 매핑
  private Integer storeId;

  // region_id INT NN UN
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(
      name = "region_id",
      nullable = false,
      foreignKey = @ForeignKey(name = "fk_store_region")
      // , columnDefinition = "INT UNSIGNED" // UN이 UNSIGNED 의미라면 주석 해제
  )
  private Region region;

  // store_name VARCHAR(45) NN
  @Column(name = "store_name", nullable = false, length = 45)
  private String storeName;

  // user_number INT NN
  @Column(name = "user_number", nullable = false)
  private Integer userNumber;

  // detail_address VARCHAR(45) NN
  @Column(name = "detail_address", nullable = false, length = 45)
  private String detailAddress;

  // 양방향 연관관계 (다른 테이블 FK가 store_id를 가리킨다고 가정)
  @OneToMany(mappedBy = "store")
  @Builder.Default
  private List<Review> reviews = new ArrayList<>();

  @OneToMany(mappedBy = "store")
  @Builder.Default
  private List<Inquiry> inquiries = new ArrayList<>();

  @OneToMany(mappedBy = "store")
  @Builder.Default
  private List<Mission> missions = new ArrayList<>();
}
