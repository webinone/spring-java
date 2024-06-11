package org.example.java_demo.repository;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import java.time.LocalDateTime;
import org.example.java_demo.config.db.QueryDslConfig;
import org.example.java_demo.model.enums.RoleType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageRequest;

@DataJpaTest
@Import(QueryDslConfig.class)
//@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MemberSignInHistoryRepositoryTest {

  @Autowired
  private MemberSignInHistoryRepository memberSignInHistoryRepository;

  @Autowired
  private TestEntityManager entityManager;

  private EntityManager em;

  private JPAQueryFactory jpaQueryFactory;

  @BeforeEach
  void setUp() {
    em = entityManager.getEntityManager();
    jpaQueryFactory = new JPAQueryFactory(em);

  }

  @Test
  public void findSignInHistoryByCriteriaTest() {

    LocalDateTime startDateTime = LocalDateTime.of(2023, 1, 1, 0, 0);
    LocalDateTime endDateTime = LocalDateTime.of(2023, 12, 31, 23, 59);

    var memberSignInHistoryEntityList = memberSignInHistoryRepository.findSearchCriteria(1L, RoleType.ADMIN, null, null,
        PageRequest.of(0, 2));

    assertThat(memberSignInHistoryEntityList).hasSize(2);

  }

}