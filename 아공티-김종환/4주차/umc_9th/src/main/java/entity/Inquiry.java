package entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.*;

@Entity
@Table(name="inquiry_tb")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Inquiry {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="inquiry_id")
  private Integer id;

  @ManyToOne(fetch=FetchType.LAZY, optional=false) //FetchType.LAZY = 지연로딩 , optional=false = 유저가 반드시 존재해야 한다
  @JoinColumn(name="user_id", foreignKey=@ForeignKey(name="fk_inquiry_user"))
  private User user;


  @Column(name="store_id", nullable=false, columnDefinition="TEXT")
  private String storeIdRaw;

  @Column(name="status", nullable=false, length=45)
  private String status;

  @Column(name="created_at")   
  private LocalDateTime createdAt;

  @Column(name="answered_at")  
  private LocalDateTime answeredAt;
}
