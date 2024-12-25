package dev.vivekraman.monolith.security.auth;

import java.util.Set;

/**
 * Implement this service to make use of API key authentication.
 */
public interface AuthService {
  public Set<String> fetchAuthoritiesForApiKey(String apiKey);
}
