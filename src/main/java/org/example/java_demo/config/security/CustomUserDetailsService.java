package org.example.java_demo.config.security;

import lombok.RequiredArgsConstructor;
import org.example.java_demo.model.convertor.MemberConverter;
import org.example.java_demo.repository.MemberRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

  private final MemberRepository memberRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    var user = memberRepository.findByEmail(username).map(MemberConverter::toDomain).orElseThrow(() -> new UsernameNotFoundException(username));
    return new CustomUserDetails(user);
  }
}
