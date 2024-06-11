package org.example.java_demo.repository;

import jakarta.annotation.Nullable;
import java.time.LocalDateTime;
import org.example.java_demo.model.domain.MemberSignInHistory;
import org.example.java_demo.model.enums.RoleType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MemberSignInHistoryRepositoryCustom {

  public Page<MemberSignInHistory> findSearchCriteria(Long memberId, @Nullable RoleType role,
                                  @Nullable LocalDateTime startDateTime, @Nullable LocalDateTime endDateTime, Pageable pageable);
}
