package dev.vivekraman.monolith.security.auth.model;

import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "auth_user")
public class AuthUser {
  private String apiKey;
  private String authority;
}
