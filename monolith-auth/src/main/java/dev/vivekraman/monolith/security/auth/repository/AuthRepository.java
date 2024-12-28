package dev.vivekraman.monolith.security.auth.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import dev.vivekraman.monolith.security.auth.model.AuthUser;
import reactor.core.publisher.Flux;

@Repository
public interface AuthRepository extends R2dbcRepository<AuthUser, String> {
  Flux<AuthUser> findByApiKey(String apiKey);
}
