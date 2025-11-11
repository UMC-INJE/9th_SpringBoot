// entity/Review.java
package entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.*;

@Entity
@Table(name="review_tb")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Review {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="review_id")
  private Integer id;

  // ✅ 관계 필드명은 'user'로 (타입은 User)
  @ManyToOne(fetch=FetchType.LAZY, optional=false)
  @JoinColumn(name="user_id", foreignKey=@ForeignKey(name="fk_review_user"))
  private User user;

  @ManyToOne(fetch=FetchType.LAZY, optional=false)
  @JoinColumn(name="store_id", foreignKey=@ForeignKey(name="fk_review_store"))
  private Store store;

  @Column(name="rating", nullable=false)
  private Integer rating;

  @Column(name="content", columnDefinition="TEXT")
  private String content;

  // DB 컬럼 오타(created_ad) 매핑 → 필드명은 createdAt 유지
  @Column(name="created_at")
  private LocalDateTime createdAt;
}
