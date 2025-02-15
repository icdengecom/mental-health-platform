package com.icdenge.exception.unchecked;

import com.icdenge.exception.ErrorDefinition;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class IllegalDataException extends RuntimeException {

  private final ErrorDefinition error;

  public static IllegalDataException createUsernameOrEmailInUse() {
    return new IllegalDataException(IllegalArgumentsError.USERNAME_OR_EMAIL_IN_USE);
  }

  public static IllegalDataException createJsonProcessing() {
    return new IllegalDataException(IllegalArgumentsError.JSON_PROCESSING);
  }

  public String getErrorLabel() {
    return error.getLabel();
  }

  @Getter
  @AllArgsConstructor
  private enum IllegalArgumentsError implements ErrorDefinition {
    USERNAME_OR_EMAIL_IN_USE("username.or.email.in.use"),
    JSON_PROCESSING("json.processing");

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
}
