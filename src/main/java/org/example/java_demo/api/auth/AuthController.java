package org.example.java_demo.api.auth;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.java_demo.api.auth.request.SignInRequest;
import org.example.java_demo.api.auth.request.SignUpRequest;
import org.example.java_demo.model.api.JwtTokenResponse;
import org.example.java_demo.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

  private final AuthService authService;

  @PostMapping("/signup")
  public void SignUp(@Valid @RequestBody SignUpRequest request) {
    request.validate();
    authService.signUp(request);
  }

  @PostMapping("/signin")
  public JwtTokenResponse SignIn(@Valid @RequestBody SignInRequest request) {
    return authService.signIn(request);
  }




}
