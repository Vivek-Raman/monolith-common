package dev.vivekraman.monolith.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

import dev.vivekraman.monolith.security.auth.AuthService;
import reactor.core.publisher.Mono;

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity(useAuthorizationManager = true)
public class SecurityConfiguration {
  @Autowired(required = false)
  private AuthService authService;

  @Value("${auth.bypass-api-key:false}")
  private boolean bypassApiKey = false;

  @Bean
  public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
    return http
      .csrf(spec -> spec.disable())
      .addFilterAt(new ApiKeyFilter(this.authService, this.bypassApiKey), SecurityWebFiltersOrder.AUTHENTICATION)
      .authenticationManager(authenticationManager())
      .build();
  }

  public static ReactiveAuthenticationManager authenticationManager() {
    return auth -> {
      String apiKey = (String) auth.getPrincipal();
      return Mono.just(auth);
    };
  }
}
