package dev.vivekraman.monolith.security.auth.service;

import java.util.List;

import reactor.core.publisher.Mono;

/**
 * Implement this service to make use of API key authentication.
 */
public interface AuthService {
  public Mono<List<String>> fetchAuthoritiesForApiKey(String apiKey);
}
