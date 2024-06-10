package org.example.java_demo.api.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.java_demo.config.security.CustomUserDetails;
import org.example.java_demo.model.domain.Member;
import org.example.java_demo.service.MemberService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
public class MemberController {

  private final MemberService memberService;

//  @PreAuthorize("hasRole('ROLE_ADMIN')")
//  @PreAuthorize("hasAnyAuthority('ROLE.GUEST')")
  @GetMapping("/me")
  public Member readMember(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
    return memberService.readMember(customUserDetails.getMember().getId());
  }


}
