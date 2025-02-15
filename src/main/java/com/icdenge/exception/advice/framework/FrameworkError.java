package com.icdenge.exception.advice.framework;

import com.icdenge.exception.ErrorDefinition;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FrameworkError implements ErrorDefinition {
  STALE_OBJECT("stale.object"),
  CONSTRAINT_VIOLATION("constraint.violation"),
  BAD_CREDENTIALS("bad.credentials"),
  AUTHENTICATION_FAILURE("authentication.failure"),
  UNIQUENESS_VIOLATION("uniqueness.violation"),
  ACCESS_DENIED("access.denied");

  private final String label;

  @Override
  public String getName() {
    return this.getClass().getSimpleName();
  }

  @Override
  public String getType() {
    return name();
  }
}
