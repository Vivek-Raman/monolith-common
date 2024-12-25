package dev.vivekraman.monolith.security.model;

import java.util.List;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class ApiKeyToken extends AbstractAuthenticationToken {
  private String apiKey;

  public ApiKeyToken(String apiKey, List<GrantedAuthority> authorities) {
    super(authorities);
    this.apiKey = apiKey;
    this.setAuthenticated(true);
  }

  public String getApiKey() {
    return this.apiKey;
  }

  @Override
  public Object getCredentials() {
    return null;
  }

  @Override
  public Object getPrincipal() {
    return this.apiKey;
  }

}
