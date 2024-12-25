package dev.vivekraman.monolith.security.config;

import org.springdoc.core.customizers.GlobalOpenApiCustomizer;
import org.springdoc.core.customizers.GlobalOperationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.method.HandlerMethod;

import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityScheme.In;
import io.swagger.v3.oas.models.security.SecurityScheme.Type;

@Configuration
public class SpringdocConfiguration {
  @Bean
  public GlobalOpenApiCustomizer securityCustomizer() {
    return openAPI -> openAPI.getComponents()
      .addSecuritySchemes("ApiKey", new SecurityScheme()
        .$ref("ApiKey")
        .in(In.HEADER)
        .name("X-API-Key")
        .type(Type.APIKEY))
        .addExtension(null, openAPI);
  }

  @Bean
  public GlobalOperationCustomizer securitySchemeCustomizer() {
    return (Operation operation, HandlerMethod handlerMethod) -> {
      if (handlerMethod.hasMethodAnnotation(PreAuthorize.class)) {
        return operation.addSecurityItem(
          new SecurityRequirement().addList("ApiKey"));
      }
      return operation;
    };
  }
}
