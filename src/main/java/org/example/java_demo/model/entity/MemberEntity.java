package org.example.java_demo.model.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.java_demo.model.enums.RoleType;

@Entity
@Table(name="tb_member")
@Builder(access = AccessLevel.PRIVATE)
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberEntity extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "email", nullable = false)
  private String email;

  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "reg_no", nullable = false)
  private String regNo;

  @Enumerated(EnumType.STRING)
  @Column(name = "role", nullable = false)
  private RoleType role;

  public void updateEmailAndPasswordAndRegNo(@Nullable String email, @Nullable String password, @Nullable String regNo) {
    Optional.ofNullable(email).ifPresent(n -> this.email = n);
    Optional.ofNullable(password).ifPresent(p -> this.password = p);
    Optional.ofNullable(regNo).ifPresent(r -> this.regNo = r);
  }

  public static MemberEntity create (String email, String password, String regNo, RoleType role) {
    return MemberEntity.builder()
        .email(email)
        .password(password)
        .regNo(regNo)
        .role(role)
        .build();
  }
}
