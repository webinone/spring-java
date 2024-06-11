package org.example.java_demo.service;

import static org.example.java_demo.model.convertor.MemberConverter.toDomain;

import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.java_demo.api.member.request.UpdateMemberRequest;
import org.example.java_demo.exception.BusinessException;
import org.example.java_demo.exception.ErrorCode;
import org.example.java_demo.model.api.PageResponse;
import org.example.java_demo.model.convertor.MemberConverter;
import org.example.java_demo.model.domain.Member;
import org.example.java_demo.model.domain.MemberSignInHistory;
import org.example.java_demo.model.enums.RoleType;
import org.example.java_demo.repository.MemberRepository;
import org.example.java_demo.repository.MemberSignInHistoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

  private final MemberRepository memberRepository;
  private final MemberSignInHistoryRepository memberSignInHistoryRepository;

//  public Member createMember(CreateMemberRequest request) {
//
//    var memberEntity = MemberEntity.create(request.name(), request.password(), request.regNo());
//    return toDomain(memberRepository.save(memberEntity));
//  }

  public Member updateMember(Long id, UpdateMemberRequest request) {

    var memberEntity = memberRepository.findById(id)
        .orElseThrow(() -> new BusinessException(ErrorCode.MEMBER_NOT_FOUND));
    memberEntity.updateEmailAndPasswordAndRegNo(request.name(), request.password(), request.regNo());

    return toDomain(memberEntity);
  }

  public void deleteMember(Long id) {
    var memberEntity = memberRepository.findById(id)
        .orElseThrow(() -> new BusinessException(ErrorCode.MEMBER_NOT_FOUND));

    memberRepository.delete(memberEntity);
  }

  public Member readMember(Long id) {
    return memberRepository.findById(id)
        .map(MemberConverter::toDomain)
        .orElseThrow(() -> new BusinessException(ErrorCode.MEMBER_NOT_FOUND));

  }

  public PageResponse<MemberSignInHistory> readMemberSignInHistory(Long id, RoleType role, LocalDateTime startDateTime, LocalDateTime endDateTime, Pageable pageable) {
    var memberSignInHistoryPage =  memberSignInHistoryRepository.findSearchCriteria(id, role, startDateTime, endDateTime, pageable);

    return new PageResponse<>(memberSignInHistoryPage.getContent(), memberSignInHistoryPage.getTotalElements(), memberSignInHistoryPage.getTotalPages(), memberSignInHistoryPage.getNumber());
  }

  public List<Member> readMembers() {
    return memberRepository.findAll().stream().map(MemberConverter::toDomain)
        .toList();
  }

  public Page<Member> readMembers(int page, int size) {
    return memberRepository.findAll(PageRequest.of(page, size)).map(MemberConverter::toDomain);
  }

}
