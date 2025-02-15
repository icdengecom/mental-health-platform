package com.icdenge.exception.advice.runtime;

import com.icdenge.exception.ErrorDefinition;
import lombok.NoArgsConstructor;

@NoArgsConstructor
class UnknownError implements ErrorDefinition {

  @Override
  public String getLabel() {
    return "unknown";
  }

  @Override
  public String getName() {
    return "RuntimeError";
  }

  @Override
  public String getType() {
    return "UnknownError";
  }
}
