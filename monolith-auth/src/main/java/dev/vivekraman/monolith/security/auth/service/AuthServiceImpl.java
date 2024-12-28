package dev.vivekraman.monolith.security.auth.service;

import java.util.List;

import org.springframework.stereotype.Service;

import dev.vivekraman.monolith.security.auth.model.AuthUser;
import dev.vivekraman.monolith.security.auth.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
  private final AuthRepository authRepository;

  @Override
  public Mono<List<String>> fetchAuthoritiesForApiKey(String apiKey) {
    return authRepository.findByApiKey(apiKey).collectList()
      .map(auths -> auths.stream().map(AuthUser::getAuthority).toList());
  }
}
