package org.example.java_demo.repository;

import java.util.Optional;
import org.example.java_demo.model.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

  public Optional<MemberEntity> findByEmail(String email);
}

