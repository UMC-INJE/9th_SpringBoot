package entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Entity
@Table(name="store_tb")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Store {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="stroe_id") // ← DB 오타 반영
  private Integer storeId;

  @ManyToOne(fetch=FetchType.LAZY, optional=false)
  @JoinColumn(name="region_id", foreignKey=@ForeignKey(name="fk_store_region"))
  private Region region;

  @Column(name="store_name", nullable=false, length=45)
  private String storeName;

  @Column(name="dascription", columnDefinition = "TEXT") // ← DB 오타 반영
  private String description;

  @OneToMany(mappedBy="store") @Builder.Default private List<Review> reviews = new ArrayList<>();
  @OneToMany(mappedBy="store") @Builder.Default private List<Inquiry> inquiries = new ArrayList<>();
  @OneToMany(mappedBy="store") @Builder.Default private List<Mission> missions = new ArrayList<>();
}
