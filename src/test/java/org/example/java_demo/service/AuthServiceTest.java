package org.example.java_demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.api.introspector.ConstructorPropertiesArbitraryIntrospector;
import java.util.Optional;
import org.example.java_demo.api.auth.request.SignInRequest;
import org.example.java_demo.api.auth.request.SignUpRequest;
import org.example.java_demo.config.security.JwtService;
import org.example.java_demo.exception.BusinessException;
import org.example.java_demo.exception.ErrorCode;
import org.example.java_demo.model.api.JwtTokenResponse;
import org.example.java_demo.model.entity.MemberEntity;
import org.example.java_demo.model.enums.RoleType;
import org.example.java_demo.repository.MemberRepository;
import org.example.java_demo.repository.MemberSignInHistoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

  @Mock
  JwtService jwtService;

  @Mock
  MemberRepository memberRepository;

  @Mock
  PasswordEncoder passwordEncoder;

  @Mock
  MemberSignInHistoryRepository memberSignInHistoryRepository;

  @Mock
  AuthenticationManager authenticationManager;

  @InjectMocks
  AuthService authService;

  FixtureMonkey fixtureMonkey;

  @BeforeEach
  void setUp() {
//    MockitoAnnotations.openMocks(this);
    fixtureMonkey = FixtureMonkey.builder()
        .objectIntrospector(ConstructorPropertiesArbitraryIntrospector.INSTANCE)
        .build();
  }

  @DisplayName(" 회원 가입 테스트 : 성공 ")
  @Test
  void signUp_SUCCESS() {

    var request = fixtureMonkey.giveMeBuilder(SignUpRequest.class)
        .set("email", "test@test.com")
        .set("password", "password")
        .set("regNo", "11111")
        .set("role", RoleType.ADMIN)
        .sample();

    var memberEntity = MemberEntity.create(request.email(), passwordEncoder.encode(request.password()), request.regNo(), request.role());

    authService.signUp(request);

    verify(memberRepository, times(1)).save(refEq(memberEntity));

  }
  
  @DisplayName(" 이미 회원가입이 되어 있는 경우")
  @Test
  void signUp_FAIL() {

    // given
    var request = fixtureMonkey.giveMeBuilder(SignUpRequest.class)
        .set("email", "test@test.com")
        .set("password", "password")
        .set("regNo", "11111")
        .set("role", RoleType.ADMIN)
        .sample();

    given(memberRepository.findByEmail(request.email()))
        .willThrow(new BusinessException(ErrorCode.MEMBER_NOT_FOUND));

    // when
    Throwable throwable = catchThrowable(() -> {
      authService.signUp(request);
    });

    // then
    assertThat(throwable).isInstanceOf(BusinessException.class)
        .hasMessage(ErrorCode.MEMBER_NOT_FOUND.getMessage());

  }

  @DisplayName(" 회원 로그인 테스트")
  @Test
  void signIn() {

    // give
    var request = fixtureMonkey.giveMeBuilder(SignInRequest.class)
        .set("email", "test@test.com")
        .set("password", "password")
        .sample();

    var memberEntity = MemberEntity.create(request.email(), passwordEncoder.encode(request.password()), "11111", RoleType.ADMIN);

    given(memberRepository.findByEmail(request.email())).willReturn(Optional.of(memberEntity));

    // when
    JwtTokenResponse jwtTokenResponse = authService.signIn(request);

    // then
    assertAll(
        () -> assertThat(jwtTokenResponse).isNotNull(),
        () -> assertThat(jwtTokenResponse.tokenType()).isEqualTo("Bearer")
    );
  }
}