package com.icdenge.exception.advice.runtime;

import static org.springframework.http.HttpStatus.*;

import com.icdenge.infrastructure.I18nMessageUtil;
import com.icdenge.exception.ErrorResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class RuntimeExceptionHandler {

  private static final UnknownError UNKNOWN_ERROR = new UnknownError();

  private final I18nMessageUtil localeHelper;

  @ExceptionHandler(Exception.class)
  @ResponseStatus(value = INTERNAL_SERVER_ERROR)
  public @ResponseBody ErrorResponseDTO internalServerError(Exception ex, HttpServletRequest req) {
    String message = localeHelper.getMessageByLocale(req, "unknown");
    ErrorResponseDTO errorResponse = ErrorResponseDTO.newError(UNKNOWN_ERROR, UUID.randomUUID(), message);
    log.error(message, ex);
    return errorResponse;
  }
}
