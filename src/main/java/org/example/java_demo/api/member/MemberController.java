package org.example.java_demo.api.member;

import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.java_demo.config.security.CustomUserDetails;
import org.example.java_demo.model.api.PageResponse;
import org.example.java_demo.model.domain.Member;
import org.example.java_demo.model.domain.MemberSignInHistory;
import org.example.java_demo.service.MemberService;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

  @GetMapping("/me/signin")
  public PageResponse<MemberSignInHistory> readMembers(
      @AuthenticationPrincipal CustomUserDetails customUserDetails,
      @RequestParam(required = false) LocalDateTime startDateTime,
      @RequestParam(required = false) LocalDateTime endDateTime,
      @RequestParam(value = "pageSize") int pageSize,
      @RequestParam(value = "pageNumber") int pageNumber
  ) {
    return memberService.readMemberSignInHistory(customUserDetails.getMember().getId(),
        customUserDetails.getMember().getRole(), startDateTime, endDateTime, PageRequest.of(pageNumber, pageSize));
  }

}
