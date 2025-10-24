package entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
    name = "review_photo_tb",
    indexes = {
        @Index(name = "idx_review_photo_review_id", columnList = "review_id")
    }
)
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ReviewPhoto {

  // review_photo_id INT PK NN AI
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "review_photo_id")
  private Integer id;

  // photo_url VARCHAR(250)  (NN 명시 없음 → nullable 허용)
  @Column(name = "photo_url", length = 250)
  private String photoUrl;

  // review_id INT NN (FK)
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(
      name = "review_id",
      nullable = false,
      foreignKey = @ForeignKey(name = "fk_review_photo_review")
  )
  private Review review;
}
