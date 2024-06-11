package org.example.java_demo.repository;

import org.example.java_demo.model.entity.MemberSignInHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberSignInHistoryRepository extends JpaRepository<MemberSignInHistoryEntity, Long>,
    MemberSignInHistoryRepositoryCustom {

}
