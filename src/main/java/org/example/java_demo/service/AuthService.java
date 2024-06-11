package org.example.java_demo.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.java_demo.api.auth.request.SignInRequest;
import org.example.java_demo.api.auth.request.SignUpRequest;
import org.example.java_demo.config.security.JwtService;
import org.example.java_demo.exception.BusinessException;
import org.example.java_demo.exception.ErrorCode;
import org.example.java_demo.model.api.JwtTokenResponse;
import org.example.java_demo.model.convertor.MemberConverter;
import org.example.java_demo.model.entity.MemberEntity;
import org.example.java_demo.model.entity.MemberSignInHistoryEntity;
import org.example.java_demo.repository.MemberRepository;
import org.example.java_demo.repository.MemberSignInHistoryRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {

  private final JwtService jwtService;
  private final MemberRepository memberRepository;
  private final MemberSignInHistoryRepository memberSignInHistoryRepository;
  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authenticationManager;

  public void signUp(SignUpRequest request) {
    validDuplicateMemberByEmail(request.email());

    var memberEntity = MemberEntity.create(request.email(), passwordEncoder.encode(request.password()), request.regNo(), request.role());
    memberRepository.save(memberEntity);
    System.out.println("finish!!");
  }

  public JwtTokenResponse signIn(SignInRequest request) {

    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.email(), request.password()));

    var memberEntity = memberRepository.findByEmail(request.email())
                        .orElseThrow(() -> new BusinessException(ErrorCode.MEMBER_NOT_FOUND));

    recordSignInHistory(memberEntity);

    return JwtTokenResponse.of(jwtService.createAccessToken(MemberConverter.toDomain(memberEntity)));
  }

  private void validDuplicateMemberByEmail(String email) {
    memberRepository.findByEmail(email).ifPresent(
        memberEntity -> {
          throw new BusinessException(ErrorCode.MEMBER_NOT_FOUND);
        });
  }

  private void recordSignInHistory(MemberEntity memberEntity) {
    var memberSignInHistoryEntity = MemberSignInHistoryEntity.create(memberEntity.getEmail(), memberEntity);
    memberSignInHistoryRepository.save(memberSignInHistoryEntity);
  }
}
