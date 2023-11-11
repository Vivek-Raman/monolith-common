package dev.vivekraman.monolith.annotation;

import dev.vivekraman.monolith.config.ModuleEnabledCondition;
import org.springframework.context.annotation.Conditional;
import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Use this annotation in place of {@link RestController}. Using the
 * provided {@link MonolithController#moduleName()}, the platform
 * can decide whether to include the controllers.
 * <br />
 * If {@code spring.profiles.include} contains the module name,
 * then the controllers will be included, else they will be inaccessible.
 * This acts as a kill-switch to all entry-points of each module.
 * <br />
 * Additionally, this enforces the base path of the controller to be the
 * {@link MonolithController#moduleName()}.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@RestController
@RequestMapping
@Conditional(ModuleEnabledCondition.class)
public @interface MonolithController {
  @AliasFor(annotation = RequestMapping.class, attribute = "value")
  String moduleName();
}
