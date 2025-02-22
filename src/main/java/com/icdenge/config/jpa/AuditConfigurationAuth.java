package com.icdenge.config.jpa;

import com.icdenge.config.security.SecurityUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class AuditConfigurationAuth {

  @Bean
  public AuditorAware<String> auditorAware() {
    return SecurityUtils::getCurrentAuditor;
  }
}
