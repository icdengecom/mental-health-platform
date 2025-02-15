package com.icdenge.config.i18n;

import java.util.Locale;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

@Configuration
public class MultiLangSupportConfig implements WebMvcConfigurer {

  @Bean
  public LocaleResolver localeResolver() {
    AcceptHeaderLocaleResolver ahlr = new AcceptHeaderLocaleResolver();
    ahlr.setDefaultLocale(Locale.US);
    return ahlr;
  }

  @Bean
  public ResourceBundleMessageSource messageSource() {
    ResourceBundleMessageSource source = new ResourceBundleMessageSource();
    source.setBasename("i18n/messages");
    source.setUseCodeAsDefaultMessage(true);
    return source;
  }
}
