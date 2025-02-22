package com.icdenge.exception.advice.managed;

import com.icdenge.infrastructure.I18nMessageUtil;
import com.icdenge.exception.ErrorResponseDTO;
import com.icdenge.exception.unchecked.*;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static com.icdenge.exception.ErrorResponseDTO.newError;
import static org.springframework.http.HttpStatus.*;

@Slf4j
@Hidden
@ControllerAdvice
@RequiredArgsConstructor
public class ManagedExceptionHandler {

  private final I18nMessageUtil localeHelper;

  @ExceptionHandler(IllegalDataException.class)
  @ResponseStatus(value = NOT_ACCEPTABLE)
  public @ResponseBody ErrorResponseDTO illegalArgumentOrData(IllegalDataException ex, HttpServletRequest req) {
    String message = localeHelper.getMessageByLocale(req, ex.getErrorLabel());
    return newError(ex.getError(), message);
  }

  @ExceptionHandler(ResourceNotFoundException.class)
  @ResponseStatus(value = NOT_FOUND)
  public @ResponseBody ErrorResponseDTO resourceNotFound(ResourceNotFoundException ex, HttpServletRequest req) {
    String msg = localeHelper.getMessageByLocale(req, ex.getErrorLabel(), ex.getEntity());
    return newError(ex.getError(), msg);
  }

  @ExceptionHandler(ReferencedEntityRemovalException.class)
  @ResponseStatus(value = BAD_REQUEST)
  public @ResponseBody ErrorResponseDTO referenceRemovalViolation(ReferencedEntityRemovalException ex, HttpServletRequest req) {
    String message = localeHelper.getMessageByLocale(req, ex.getErrorLabel(), ex.getRemovalEntity(), ex.getReferencingEntity());
    return newError(ex.getError(), message);
  }

}
