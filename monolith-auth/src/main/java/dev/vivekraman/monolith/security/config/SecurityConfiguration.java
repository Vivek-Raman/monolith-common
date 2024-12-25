package dev.vivekraman.monolith.security.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

import dev.vivekraman.monolith.security.repository.AuthRepository;
import reactor.core.publisher.Mono;

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity(useAuthorizationManager = true)
public class SecurityConfiguration {
  @Value("${auth.bypass-api-key:false}")
  private boolean bypassApiKey = false;

  @Bean
  public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http, AuthRepository authRepository) {
    return http
      .csrf(spec -> spec.disable())
      .addFilterAt(new ApiKeyFilter(bypassApiKey), SecurityWebFiltersOrder.AUTHENTICATION)
      .authenticationManager(authenticationManager(authRepository))
      .build();
  }

  public static ReactiveAuthenticationManager authenticationManager(AuthRepository authRepository) {
    return auth -> {
      String apiKey = (String) auth.getPrincipal();
      authRepository.asd();
      return Mono.just(auth);
    };
  }
}
