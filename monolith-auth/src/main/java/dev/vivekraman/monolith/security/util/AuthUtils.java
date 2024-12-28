package dev.vivekraman.monolith.security.util;

import org.springframework.security.core.context.ReactiveSecurityContextHolder;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import reactor.core.publisher.Mono;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthUtils {
  public static Mono<String> fetchApiKey() {
    return ReactiveSecurityContextHolder.getContext().map(context -> {
      return (String) context.getAuthentication().getPrincipal();
    });
  }
}
