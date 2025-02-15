package com.icdenge.exception.unchecked;

import com.icdenge.exception.ErrorDefinition;
import com.icdenge.common.enums.SystemEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ReferencedEntityRemovalException extends RuntimeException {

  private static final ErrorDefinition ERROR = new ReferencedEntityRemovalError();

  private final SystemEntity removalEntity;
  private final SystemEntity referencingEntity;

  public String getErrorLabel() {
    return getError().getLabel();
  }

  public ErrorDefinition getError() {
    return ERROR;
  }

  private static final class ReferencedEntityRemovalError implements ErrorDefinition {
    @Override
    public String getLabel() {
      return "referenced.entity.removal";
    }

    @Override
    public String getName() {
      return "REFERENCE_REMOVAL_VIOLATION";
    }

    @Override
    public String getType() {
      return this.getClass().getSimpleName();
    }
  }

}
