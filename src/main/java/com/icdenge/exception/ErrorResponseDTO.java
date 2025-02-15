package com.icdenge.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorResponseDTO {

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy hh:mm:ss")
  private final LocalDateTime timestamp = LocalDateTime.now();

  private String message;
  private String errorCode;
  private String errorName;
  private String errorType;
  private UUID traceId;

  public static ErrorResponseDTO newError(ErrorDefinition errDef, UUID traceId, String message) {
    return ErrorResponseDTO.builder()
        .message(message)
        .traceId(traceId)
        .errorCode(errDef.getLabel())
        .errorName(errDef.getName())
        .errorType(errDef.getType())
        .build();
  }

  public static ErrorResponseDTO newError(ErrorDefinition errDef, String message) {
    return ErrorResponseDTO.builder()
        .message(message)
        .errorCode(errDef.getLabel())
        .errorName(errDef.getName())
        .errorType(errDef.getType())
        .build();
  }
}
