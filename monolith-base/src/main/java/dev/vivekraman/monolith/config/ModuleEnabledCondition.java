package dev.vivekraman.monolith.config;

import dev.vivekraman.monolith.annotation.MonolithController;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public class ModuleEnabledCondition implements Condition {
  @Override
  public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
    String moduleName = Optional
        .ofNullable(metadata.getAnnotationAttributes(MonolithController.class.getName()))
        .map(attributes -> attributes.get("moduleName"))
        .map(Object::toString)
        .orElse(null);

    boolean moduleActive = Arrays.stream(context.getEnvironment().getActiveProfiles())
        .anyMatch(profile -> Objects.equals(profile, moduleName));
    return moduleActive;
  }
}
