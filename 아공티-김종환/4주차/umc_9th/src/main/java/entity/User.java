package entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_tb")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class User {

  // user_id INT PK NN AI
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  private Integer id;

  // user_name VARCHAR(50) NN
  @Column(name = "user_name", nullable = false, length = 50)
  private String userName;

  // gender VARCHAR(45) NN  (스키마가 ENUM이 아니라 문자열이므로 String으로 매핑)
  @Column(name = "gender", nullable = false, length = 45)
  private String gender;

  // birth_date DATE NN
  @Column(name = "birth_date", nullable = false)
  private LocalDate birthDate;

  // address VARCHAR(255) NN
  @Column(name = "address", nullable = false, length = 255)
  private String address;

  // detail_address VARCHAR(45) NN
  @Column(name = "detail_address", nullable = false, length = 45)
  private String detailAddress;

  // social_uid VARCHAR(45) NN
  @Column(name = "social_uid", nullable = false, length = 45)
  private String socialUid;

  // social_type ENUM('LOCAL','KAKAO','NAVER','GOOGLE','APPLE')
  // 스키마 그대로 매핑되도록 columnDefinition 지정 + 자바 enum은 STRING 저장
  public enum SocialType { LOCAL, KAKAO, NAVER, GOOGLE, APPLE }

  @Enumerated(EnumType.STRING)
  @Column(
      name = "social_type",
      columnDefinition = "ENUM('LOCAL','KAKAO','NAVER','GOOGLE','APPLE')"
      // nullable 미지정(스키마에 NN 표기 없음)
  )
  private SocialType socialType;

  // point INT NN
  @Column(name = "point", nullable = false)
  private Integer point;

  // email VARCHAR(50) (nullable 허용)
  @Column(name = "email", length = 50)
  private String email;

  // phone VARCHAR(45) (nullable 허용)
  @Column(name = "phone", length = 45)
  private String phone;

  // deleted_at TIMESTAMP(3) (nullable 허용)
  @Column(name = "deleted_at", columnDefinition = "TIMESTAMP(3)")
  private LocalDateTime deletedAt;

  // updated_at TIMESTAMP(3) (nullable 허용)
  @Column(name = "updated_at", columnDefinition = "TIMESTAMP(3)")
  private LocalDateTime updatedAt;
}
