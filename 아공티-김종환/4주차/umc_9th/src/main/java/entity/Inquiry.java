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

   
    @ManyToOne(fetch=FetchType.LAZY, optional=false)
    @JoinColumn(name="user_id", foreignKey=@ForeignKey(name="fk_inquiry_user"))
    private User user;

    
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
