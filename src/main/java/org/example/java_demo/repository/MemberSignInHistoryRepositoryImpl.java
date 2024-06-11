package org.example.java_demo.repository;

import static org.example.java_demo.common.utils.QuerydslUtils.eq;
import static org.example.java_demo.common.utils.QuerydslUtils.goe;
import static org.example.java_demo.common.utils.QuerydslUtils.loe;
import static org.example.java_demo.model.entity.QMemberSignInHistoryEntity.memberSignInHistoryEntity;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.java_demo.model.domain.MemberSignInHistory;
import org.example.java_demo.model.enums.RoleType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

@RequiredArgsConstructor
public class MemberSignInHistoryRepositoryImpl implements MemberSignInHistoryRepositoryCustom {

  private final JPAQueryFactory factory;

  @Override
  public Page<MemberSignInHistory> findSearchCriteria(Long memberId, @Nullable RoleType role,
      @Nullable LocalDateTime startDateTime, @Nullable LocalDateTime endDateTime, Pageable pageable) {

    List<MemberSignInHistory> histories =  factory
                        .select(
                            Projections.constructor(
                                MemberSignInHistory.class,
                                memberSignInHistoryEntity.id,
                                memberSignInHistoryEntity.email,
                                memberSignInHistoryEntity.memberEntity.regNo,
                                memberSignInHistoryEntity.memberEntity.role,
                                memberSignInHistoryEntity.createdAt,
                                memberSignInHistoryEntity.updatedAt
                            )
                          )
                          .from(memberSignInHistoryEntity)
                          .where(
                              eq(memberSignInHistoryEntity.memberEntity.id, memberId),
                              eq(memberSignInHistoryEntity.memberEntity.role, role),
                              goe(memberSignInHistoryEntity.createdAt, startDateTime),
                              loe(memberSignInHistoryEntity.createdAt, endDateTime)
                          )
                          .offset(pageable.getOffset())
                          .limit(pageable.getPageSize())
                          .fetch();

    long total = factory.selectFrom(memberSignInHistoryEntity)
                  .where(
                      eq(memberSignInHistoryEntity.memberEntity.id, memberId),
                      eq(memberSignInHistoryEntity.memberEntity.role, role),
                      goe(memberSignInHistoryEntity.createdAt, startDateTime),
                      loe(memberSignInHistoryEntity.createdAt, endDateTime)
                  ).fetchCount();

    return new PageImpl<>(histories, pageable, total);
  }

}
