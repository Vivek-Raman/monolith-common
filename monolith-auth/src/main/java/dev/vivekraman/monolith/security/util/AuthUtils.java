package dev.vivekraman.monolith.security.util;

import org.springframework.security.core.context.ReactiveSecurityContextHolder;

import reactor.core.publisher.Mono;

public class AuthUtils {
  private AuthUtils() {}

  public static Mono<String> fetchApiKey() {
    return ReactiveSecurityContextHolder.getContext().map(context -> {
      return (String) context.getAuthentication().getPrincipal();
    });
  }
}
