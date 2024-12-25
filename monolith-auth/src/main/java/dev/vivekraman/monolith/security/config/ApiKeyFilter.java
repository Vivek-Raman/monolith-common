package dev.vivekraman.monolith.security.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import dev.vivekraman.monolith.security.auth.AuthService;
import dev.vivekraman.monolith.security.model.ApiKeyToken;
import reactor.core.publisher.Mono;

public class ApiKeyFilter implements WebFilter {
  private final AuthService authService;
  private final boolean bypass;

  public ApiKeyFilter(AuthService authService, boolean bypass) {
    this.authService = authService;
    this.bypass = bypass;
  }

  @Override
  public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
    List<String> apiKeys = exchange.getRequest().getHeaders().get("X-API-Key");
    if (CollectionUtils.isEmpty(apiKeys)) {
      return chain.filter(exchange);
    }
    String apiKey = apiKeys.getFirst();

    List<GrantedAuthority> authorities = new ArrayList<>();
    if (!bypass || Objects.isNull(this.authService)) {
      this.authService.fetchAuthoritiesForApiKey(apiKey).forEach(authority -> {
        authorities.add(new SimpleGrantedAuthority(authority));
      });
    }

    return chain.filter(exchange)
      .contextWrite(ReactiveSecurityContextHolder.withAuthentication(
        new ApiKeyToken(apiKey, authorities)));
  }
}
