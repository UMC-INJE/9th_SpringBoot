package entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.*;

@Entity
@Table(name="inquiry_tb")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Inquiry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="inquiry_id")
    private Integer id;

    // ✅ 유저와 N:1 (필수 관계)
    @ManyToOne(fetch=FetchType.LAZY, optional=false)
    @JoinColumn(name="user_id", foreignKey=@ForeignKey(name="fk_inquiry_user"))
    private User user;

    // ✅ Store와 N:1 (에러의 핵심 해결 부분)
    // 기존 storeIdRaw 제거 → 실제 Store 엔티티와 연결
    @ManyToOne(fetch=FetchType.LAZY, optional=false)
    @JoinColumn(name="store_id", foreignKey=@ForeignKey(name="fk_inquiry_store"))
    private Store store;

    @Column(name="status", nullable=false, length=45)
    private String status;

    @Column(name="created_at")
    private LocalDateTime createdAt;

    @Column(name="answered_at")
    private LocalDateTime answeredAt;
}
