package com.icdenge.infrastructure;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Locale;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.LocaleResolver;

@Component
@RequiredArgsConstructor
public class I18nMessageUtil {

  private final MessageSource messageSource;
  private final LocaleResolver localeResolver;

  public String getMessageByLocale(HttpServletRequest req, String label) {
    Locale locale = localeResolver.resolveLocale(req);
    return messageSource.getMessage(label, null, locale);
  }

  public String getMessageByLocale(HttpServletRequest req, String label, Object... args) {
    Locale locale = localeResolver.resolveLocale(req);
    return messageSource.getMessage(label, args, locale);
  }

  public String getMessageByLocale(Locale locale, String label) {
    return messageSource.getMessage(label, null, locale);
  }
}