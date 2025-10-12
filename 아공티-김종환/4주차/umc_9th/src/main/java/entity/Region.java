package entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Entity
@Table(
  name = "region_tb",
  uniqueConstraints = @UniqueConstraint(name="uk_region_name", columnNames = "region_name")
)
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Region {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="region_id")
  private Integer id;

  @Column(name="region_name", nullable=false, length=100)
  private String regionName;

  @OneToMany(mappedBy="region")
  @Builder.Default private List<Store> stores = new ArrayList<>();
}
