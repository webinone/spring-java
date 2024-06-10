package org.example.java_demo.model.convertor;

import org.example.java_demo.model.domain.Member;
import org.example.java_demo.model.entity.MemberEntity;

public class MemberConverter {

  public static Member toDomain(MemberEntity memberEntity) {
    return Member.of(
        memberEntity.getId(),
        memberEntity.getEmail(),
        memberEntity.getPassword(),
        memberEntity.getRegNo(),
        memberEntity.getRole()
        );
  }

//  public static MemberEntity toEntity(Member member) {
//    return MemberEntity.create(member.getName(), member.getPassword(), member.getRegNo());
//  }

}
