package entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "reply_tb")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reply_id")
    private Integer replyId;

    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "review_id", foreignKey = @ForeignKey(name = "fk_reply_review"))
    private Review review;
}
