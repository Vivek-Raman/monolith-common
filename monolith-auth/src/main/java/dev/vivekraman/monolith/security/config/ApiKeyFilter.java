package dev.vivekraman.monolith.security.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import dev.vivekraman.monolith.security.model.ApiKeyToken;
import reactor.core.publisher.Mono;

public class ApiKeyFilter implements WebFilter {
  private final boolean bypass;

  public ApiKeyFilter(boolean bypass) {
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
    authorities.add(new SimpleGrantedAuthority("any"));
    return chain.filter(exchange)
      .contextWrite(ReactiveSecurityContextHolder.withAuthentication(
        new ApiKeyToken(apiKey, authorities)));
  }
}
