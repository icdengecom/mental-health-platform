package com.icdenge.exception.unchecked;

import com.icdenge.exception.ErrorDefinition;
import com.icdenge.common.enums.SystemEntity;
import lombok.Getter;

@Getter
public class ResourceNotFoundException extends RuntimeException {

  private static final ErrorDefinition ERROR = new ResourceNotFoundError();

  private final SystemEntity entity;

  private ResourceNotFoundException(SystemEntity entity) {
    super();
    this.entity = entity;
  }

  public static ResourceNotFoundException user() {
    return new ResourceNotFoundException(SystemEntity.USER);
  }

  public ErrorDefinition getError() {
    return ERROR;
  }

  public String getErrorLabel() {
    return getError().getLabel();
  }

  private static final class ResourceNotFoundError implements ErrorDefinition {

    @Override
    public String getLabel() {
      return "resource.not.found";
    }

    @Override
    public String getName() {
      return "RESOURCE_NOT_FOUND";
    }

    @Override
    public String getType() {
      return this.getClass().getSimpleName();
    }
  }


}
