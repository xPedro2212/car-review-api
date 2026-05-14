package com.pedro.carreview.service;

import com.pedro.carreview.config.JwtService;
import com.pedro.carreview.dto.AuthRequest;
import com.pedro.carreview.dto.AuthResponse;
import com.pedro.carreview.model.User;
import com.pedro.carreview.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;

  public AuthResponse register(User user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    userRepository.save(user);
    return new AuthResponse(jwtService.generateToken(user.getEmail()));
  }

  public AuthResponse login(AuthRequest request) {
    User user =
        userRepository
            .findByEmail(request.getEmail())
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

    if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
      throw new RuntimeException("Senha incorreta");
    }
    return new AuthResponse(jwtService.generateToken(user.getEmail()));
  }
}
