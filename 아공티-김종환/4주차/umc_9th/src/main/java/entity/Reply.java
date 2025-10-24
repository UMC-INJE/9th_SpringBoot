package entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
    name = "reply_tb"
    // 만약 review_id가 UNIQUE(리뷰당 답글 1개)라면 아래 주석 해제
    // , uniqueConstraints = @UniqueConstraint(name = "uk_reply_review", columnNames = "review_id")
)
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Reply {

  // reply_id PK NN AI
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "reply_id")
  private Integer id;

  // content TEXT NN
  @Column(name = "content", nullable = false, columnDefinition = "TEXT")
  private String content;

  // review_id INT NN UN
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(
      name = "review_id",
      nullable = false,
      foreignKey = @ForeignKey(name = "fk_reply_review")
      // , columnDefinition = "INT UNSIGNED" // UN이 UNSIGNED 의미라면 주석 해제
  )
  private Review review;
}
