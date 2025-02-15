package com.icdenge.exception.advice.framework;

import com.icdenge.infrastructure.I18nMessageUtil;
import com.icdenge.exception.ErrorResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.StaleObjectStateException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static com.icdenge.exception.ErrorResponseDTO.newError;
import static org.springframework.http.HttpStatus.*;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class FrameworkExceptionHandler {

  private final I18nMessageUtil localeHelper;

  @ExceptionHandler(ObjectOptimisticLockingFailureException.class)
  @ResponseStatus(value = CONFLICT)
  public @ResponseBody ErrorResponseDTO conflict(StaleObjectStateException ex, HttpServletRequest req) {
    String message = localeHelper.getMessageByLocale(req, FrameworkError.STALE_OBJECT.getLabel());
    return newError(FrameworkError.STALE_OBJECT, message);
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  @ResponseStatus(value = NOT_ACCEPTABLE)
  public @ResponseBody ErrorResponseDTO constraintViolation(DataIntegrityViolationException ex, HttpServletRequest req) {
    String message = localeHelper.getMessageByLocale(req, FrameworkError.CONSTRAINT_VIOLATION.getLabel());
    return newError(FrameworkError.CONSTRAINT_VIOLATION, message);
  }

  @ExceptionHandler(InternalAuthenticationServiceException.class)
  @ResponseStatus(value = UNAUTHORIZED)
  public @ResponseBody ErrorResponseDTO authenticationError(InternalAuthenticationServiceException ex, HttpServletRequest req) {
    String message = localeHelper.getMessageByLocale(req, FrameworkError.AUTHENTICATION_FAILURE.getLabel());
    return newError(FrameworkError.AUTHENTICATION_FAILURE, message);
  }

  @ExceptionHandler(BadCredentialsException.class)
  @ResponseStatus(value = UNAUTHORIZED)
  public @ResponseBody ErrorResponseDTO incorrectPassword(InternalAuthenticationServiceException ex, HttpServletRequest req) {
    String message = localeHelper.getMessageByLocale(req, FrameworkError.BAD_CREDENTIALS.getLabel());
    return newError(FrameworkError.BAD_CREDENTIALS, message);
  }

  @ExceptionHandler(AccessDeniedException.class)
  @ResponseStatus(value = FORBIDDEN)
  public @ResponseBody ErrorResponseDTO handleAccessDeniedException(AccessDeniedException ex, HttpServletRequest req) {
    String message = localeHelper.getMessageByLocale(req, FrameworkError.ACCESS_DENIED.getLabel());
    return newError(FrameworkError.ACCESS_DENIED, message);
  }

}
